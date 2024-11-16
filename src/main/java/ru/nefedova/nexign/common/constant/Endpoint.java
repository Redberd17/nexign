package ru.nefedova.nexign.common.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Endpoint {

    public static final String API_V1 = "/api/v1";

    public static class Task {

        public static final String TASKS = API_V1 + "/tasks";
        public static final String TASKS_BY_ID_PATTERN = TASKS + "/{id}";
        public static final String TASKS_STATUS_BY_ID = TASKS_BY_ID_PATTERN + "/status";

    }

    public static class Test {

        public static final String SEND = API_V1 + "/send";

    }

}
