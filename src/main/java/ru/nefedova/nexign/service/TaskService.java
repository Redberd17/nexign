package ru.nefedova.nexign.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.stereotype.Service;
import ru.nefedova.nexign.common.enumeration.TaskStatus;
import ru.nefedova.nexign.output.persistance.TaskRepository;
import ru.nefedova.nexign.output.persistance.model.TaskEntity;
import ru.nefedova.nexign.util.LocalizationService;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    private final RedisService redisService;

    private final LocalizationService localizationService;

    public List<TaskEntity> getAllTasks() {
        return taskRepository.findAllByOrderByUpdateDateDesc();
    }

    public TaskEntity getTaskByIdOrThrow(long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                        localizationService.getMessage("task.notExistsById", id)
                ));
    }

    public void existsTaskByIdOrThrow(long id) {
        if (!taskRepository.existsById(id)) {
            throw new NoSuchElementException(
                    localizationService.getMessage("task.notExistsById", id)
            );
        }
    }

    public TaskEntity saveTask(TaskEntity entity) {
        return taskRepository.save(entity);
    }

    public void saveTaskWithStatus(TaskEntity entity, TaskStatus status) {
        entity.setStatus(status);


        if (status.equals(TaskStatus.DONE)) {
            entity.setFinishDate(Instant.now());
        }

        taskRepository.save(entity);
    }

    public String deleteByIdOrThrow(long taskId) {
        existsTaskByIdOrThrow(taskId);

        final RLock lock = redisService.getLockByTaskId(taskId);

        try {
            if (lock.tryLock()) {
                try {
                    log.info("Задача с id {} заблокирована", taskId);
                    taskRepository.deleteById(taskId);
                    return localizationService.getMessage("task.deleteByIdSuccess", taskId);
                } finally {
                    lock.unlock();
                    log.info("Задача с id {} освобождена", taskId);
                }
            } else {
                log.info("Не удалось захватить блокировку для задачи {}", taskId);
                return localizationService.getMessage("task.cannotDeleteById", taskId);
            }

        } catch (Exception e) {
            log.info(localizationService.getMessage("task.cannotDeleteById", taskId));
            Thread.currentThread().interrupt();
            return localizationService.getMessage("task.cannotDeleteById", taskId);
        }
    }

}
