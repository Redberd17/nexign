package ru.nefedova.nexign.input.web.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import ru.nefedova.nexign.common.enumeration.TaskStatus;

@Schema(description = "Модель (укороченная) ответа для конкртеной задачи")
@Builder
public record TaskShortResponse(

        @Schema(description = "Идентификатор задачи")
        @NotNull
        Long id,

        @Schema(description = "Статус задачи")
        @NotNull
        TaskStatus status

) {
}
