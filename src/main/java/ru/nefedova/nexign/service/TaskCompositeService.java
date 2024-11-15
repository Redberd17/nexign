package ru.nefedova.nexign.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nefedova.nexign.input.web.model.TaskRequest;
import ru.nefedova.nexign.input.web.model.TaskResponse;
import ru.nefedova.nexign.mapper.TaskMapper;
import ru.nefedova.nexign.output.persistance.model.TaskEntity;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskCompositeService {

    private final TaskService taskService;

    private final TaskMapper taskMapper;

    public List<TaskResponse> getAllTasks() {
        final List<TaskEntity> tasks = taskService.getAllTasks();

        return tasks.stream()
                .map(taskMapper::toTaskResponse)
                .toList();
    }

    public TaskResponse getTaskById(long id) {
        final TaskEntity task = taskService.getTaskByIdOrThrow(id);

        return taskMapper.toTaskResponse(task);
    }

    public Long createTask(TaskRequest request) {
        final TaskEntity task = taskService.saveTask(
                taskMapper.toTaskEntity(request)
        );

        return task.getId();
    }

}
