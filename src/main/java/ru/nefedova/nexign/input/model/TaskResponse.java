package ru.nefedova.nexign.input.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import ru.nefedova.nexign.common.enumeration.TaskStatus;

@Schema(description = "Модель ответа для конкртеной задачи")
@Builder
public record TaskResponse(

        @Schema(description = "Идентификатор задачи")
        @NotNull
        Long id,

        @Schema(description = "Наименование задачи")
        @NotNull
        String name,

        @Schema(description = "Время выполнения задачи")
        @NotNull
        Long duration,

        @Schema(description = "Статус задачи")
        @NotNull
        TaskStatus status

) {
}
