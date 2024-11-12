package ru.nefedova.nexign.input.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import ru.nefedova.nexign.common.enumeration.TaskStatus;

@Schema(description = "Запрос на создание задачи")
@Builder
public record TaskRequest(

        @Schema(description = "Наименование задачи")
        @NotNull
        @Size(max = 255)
        String name,

        @Schema(description = "Время выполнения задачи")
        @NotNull
        Long duration,

        @Schema(description = "Статус задачи")
        @NotNull
        TaskStatus status

) {
}
