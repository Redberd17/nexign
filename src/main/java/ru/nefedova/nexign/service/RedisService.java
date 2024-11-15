package ru.nefedova.nexign.service;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import static ru.nefedova.nexign.common.constant.Constant.REDIS_LOCK_NAME;

@RequiredArgsConstructor
@Service
public class RedisService {

    private final RedissonClient redissonClient;

    public RLock getLockByTaskId(Long taskId) {
        return redissonClient.getLock(REDIS_LOCK_NAME.formatted(taskId));
    }

}
