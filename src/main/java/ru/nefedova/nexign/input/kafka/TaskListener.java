package ru.nefedova.nexign.input.kafka;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.nefedova.nexign.input.kafka.model.TaskModel;
import ru.nefedova.nexign.mapper.TaskMapper;
import ru.nefedova.nexign.output.persistance.model.TaskEntity;
import ru.nefedova.nexign.output.worker.TaskWorker;
import ru.nefedova.nexign.service.TaskService;

import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
public class TaskListener {

    private final TaskService taskService;

    private final TaskMapper taskMapper;

    private final TaskWorker worker;

    private final Validator validator;

    @KafkaListener(topics = "tasks", groupId = "task_group")
    public void listen(TaskModel message) {
        log.info("Получено сообщение: {}", message);

        validateMessage(message);

        final TaskEntity task = taskMapper.toTaskEntity(message);

        taskService.saveTask(task);

        worker.executeWorkers(task);
    }

    private void validateMessage(TaskModel message) {
        Set<ConstraintViolation<TaskModel>> violations = validator.validate(message);

        if (!violations.isEmpty()) {
            StringBuilder errors = new StringBuilder();
            for (ConstraintViolation<TaskModel> violation : violations) {
                errors.append(violation.getPropertyPath())
                        .append(": ")
                        .append(violation.getMessage())
                        .append("; ");
            }
            throw new IllegalArgumentException("Произошла ошибка валидации TaskModel: " + errors);
        }
    }

}
