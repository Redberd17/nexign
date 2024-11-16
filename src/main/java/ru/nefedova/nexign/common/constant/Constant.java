package ru.nefedova.nexign.common.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constant {

    public static final String REDIS_LOCK_NAME = "lock-task-%s";

    public static final String KAFKA_TOPIC_TASKS = "tasks";

}
