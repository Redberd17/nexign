package ru.nefedova.nexign.input.kafka.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskModel {

    @NotNull
    private String name;

    @NotNull
    private Long duration;

}
