package ru.nefedova.nexign.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import ru.nefedova.nexign.input.kafka.model.TaskModel;
import ru.nefedova.nexign.input.web.model.TaskRequest;
import ru.nefedova.nexign.input.web.model.TaskResponse;
import ru.nefedova.nexign.input.web.model.TaskShortResponse;
import ru.nefedova.nexign.output.persistance.model.TaskEntity;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TaskMapper {

    @Mapping(source = "createDate", target = "createDate", qualifiedByName = "instanceToOffset")
    @Mapping(source = "updateDate", target = "updateDate", qualifiedByName = "instanceToOffset")
    @Mapping(source = "finishDate", target = "finishDate", qualifiedByName = "instanceToOffset")
    TaskResponse toTaskResponse(TaskEntity task);

    TaskShortResponse toTaskShortResponse(TaskEntity task);

    TaskEntity toTaskEntity(TaskRequest request);

    TaskEntity toTaskEntity(TaskModel message);

    @Named("instanceToOffset")
    static OffsetDateTime instanceToOffset(Instant instant) {
        return instant == null
                ? null
                : OffsetDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

}
