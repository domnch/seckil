package com.dayup.seckil.redis;

import com.dayup.seckil.model.User;
import org.springframework.stereotype.Component;

/**
 * @author domn
 * @version 1.0
 * @date 2020/1/24 16:41
 */
@Component
public class UserRedis extends BaseRedis<User> {

    private static final String REDIS_KEY = "com.dayup.seckil.redis.UserRedis";

    @Override
    protected String getRedisKey() {
        return REDIS_KEY;
    }
}
