package ru.nefedova.nexign.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nefedova.nexign.common.enumeration.TaskStatus;
import ru.nefedova.nexign.output.persistance.TaskRepository;
import ru.nefedova.nexign.output.persistance.model.TaskEntity;
import ru.nefedova.nexign.util.LocalizationService;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    private final LocalizationService localizationService;

    public List<TaskEntity> getAllTasks() {
        return taskRepository.findAll();
    }

    public TaskEntity getTaskByIdOrThrow(long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                        localizationService.getMessage("task.notExistsById", id)
                ));
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

}
