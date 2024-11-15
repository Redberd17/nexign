package ru.nefedova.nexign.mapper;

import org.springframework.stereotype.Component;
import ru.nefedova.nexign.common.enumeration.TaskStatus;
import ru.nefedova.nexign.input.kafka.model.TaskModel;
import ru.nefedova.nexign.input.web.model.TaskRequest;
import ru.nefedova.nexign.input.web.model.TaskResponse;
import ru.nefedova.nexign.output.persistance.model.TaskEntity;

import java.time.OffsetDateTime;
import java.time.ZoneId;

@Component
public class TaskMapper {

    public TaskResponse toTaskResponse(TaskEntity task) {
        return TaskResponse.builder()
                .id(task.getId())
                .name(task.getName())
                .duration(task.getDuration())
                .status(task.getStatus())
                .createDate(OffsetDateTime.ofInstant(task.getCreateDate(), ZoneId.systemDefault()))
                .updateDate(OffsetDateTime.ofInstant(task.getUpdateDate(), ZoneId.systemDefault()))
                .finishDate(task.getFinishDate() == null
                                    ? null
                                    : OffsetDateTime.ofInstant(task.getFinishDate(), ZoneId.systemDefault()))
                .build();
    }

    public TaskEntity toTaskEntity(TaskRequest request) {
        final TaskEntity task = new TaskEntity();

        task.setName(request.name());
        task.setDuration(request.duration());
        task.setStatus(request.status());

        return task;
    }

    public TaskEntity toTaskEntity(TaskModel message) {
        final TaskEntity task = new TaskEntity();

        task.setName(message.getName());
        task.setDuration(message.getDuration());
        task.setStatus(TaskStatus.NEW);

        return task;
    }

}
