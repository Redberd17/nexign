package ru.nefedova.nexign.common.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Endpoint {

    public static class Task {

        public static final String TASKS = "/tasks";
        public static final String TASKS_BY_ID_PATTERN = TASKS + "/{id}";

    }

}
