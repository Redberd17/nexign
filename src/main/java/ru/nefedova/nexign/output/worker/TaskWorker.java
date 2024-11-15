package ru.nefedova.nexign.output.worker;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.nefedova.nexign.common.enumeration.TaskStatus;
import ru.nefedova.nexign.output.persistance.model.TaskEntity;
import ru.nefedova.nexign.service.RedisService;
import ru.nefedova.nexign.service.TaskService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Service
public class TaskWorker {

    private final TaskService taskService;

    private final RedisService redisService;

    private ExecutorService executorService;

    @Value("${worker.count}")
    private int workerCount;

    @PostConstruct
    private void init() {
        executorService = Executors.newFixedThreadPool(workerCount);
    }

    public void executeWorkers(TaskEntity task) {
        executorService.execute(() -> {
            final RLock lock = redisService.getLockByTaskId(task.getId());

            try {
                tryLockTask(lock, task);

            } catch (InterruptedException e) {
                taskService.saveTaskWithStatus(task, TaskStatus.FILED);

                Thread.currentThread().interrupt();
            }

        });
    }

    private void tryLockTask(RLock lock, TaskEntity task) throws InterruptedException {
        final Long taskId = task.getId();

        if (lock.tryLock(2, 1, TimeUnit.MINUTES)) {
            try {
                log.info("Задача с id {} заблокирована", taskId);

                taskService.saveTaskWithStatus(task, TaskStatus.IN_PROGRESS);

                Thread.sleep(task.getDuration());

                taskService.saveTaskWithStatus(task, TaskStatus.DONE);
            } finally {
                lock.unlock();
                log.info("Задача с id {} освобождена", taskId);
            }
        } else {
            log.info("Не удалось захватить блокировку для задачи {}", taskId);
        }
    }

}
