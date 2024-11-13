package ru.nefedova.nexign.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nefedova.nexign.common.exception.EntryNotFoundException;
import ru.nefedova.nexign.input.model.TaskRequest;
import ru.nefedova.nexign.input.model.TaskResponse;
import ru.nefedova.nexign.mapper.TaskMapper;
import ru.nefedova.nexign.output.persistance.TaskRepository;
import ru.nefedova.nexign.output.persistance.model.TaskEntity;
import ru.nefedova.nexign.util.LocalizationService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;

    private final LocalizationService localizationService;

    public List<TaskResponse> getAllTasks() {
        final List<TaskEntity> tasks = taskRepository.findAll();

        return tasks.stream()
                .map(taskMapper::toTaskResponse)
                .toList();
    }

    public TaskResponse getTaskById(long id) {
        final TaskEntity task = taskRepository.findById(id)
                .orElseThrow(() -> new EntryNotFoundException(
                        localizationService.getMessage("task.notExistsById", id)
                ));

        return taskMapper.toTaskResponse(task);
    }

    public Long createTask(TaskRequest request) {
        final TaskEntity task = taskRepository.save(
                taskMapper.toTaskEntity(request)
        );

        return task.getId();
    }

}
