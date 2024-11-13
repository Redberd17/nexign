package ru.nefedova.nexign.mapper;

import org.springframework.stereotype.Component;
import ru.nefedova.nexign.input.model.TaskRequest;
import ru.nefedova.nexign.input.model.TaskResponse;
import ru.nefedova.nexign.output.persistance.model.TaskEntity;

import java.time.Duration;

@Component
public class TaskMapper {

    public TaskResponse toTaskResponse(TaskEntity task) {
        return TaskResponse.builder()
                .id(task.getId())
                .name(task.getName())
                .duration(task.getDuration())
                .status(task.getStatus())
                .build();
    }

    public TaskEntity toTaskEntity(TaskRequest request) {
        final TaskEntity task = new TaskEntity();

        task.setName(request.name());
        task.setDuration(request.duration());
        task.setStatus(request.status());

        return task;
    }

}
