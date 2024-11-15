package ru.nefedova.nexign.input.web.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import ru.nefedova.nexign.common.enumeration.TaskStatus;

import java.time.OffsetDateTime;

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
        TaskStatus status,

        @Schema(description = "Дата создания задачи")
        @NotNull
        OffsetDateTime createDate,

        @Schema(description = "Дата обновления задачи")
        @NotNull
        OffsetDateTime updateDate,

        @Schema(description = "Дата завершения задачи")
        @Nullable
        OffsetDateTime finishDate

) {
}
