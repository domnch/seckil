package com.dayup.seckil.redis;

import com.dayup.seckil.model.User;
import com.dayup.seckil.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author domn
 * @version 1.0
 * @date 2020/1/24 16:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRedisTest {

    @Autowired
    public UserService userService;

    @Autowired
    public UserRedis userRedis;

    @Test
    public void getRedisKey() {
        User user = new User("alex2", "123456");
        userRedis.put(user.getUsername(), user, -1);
    }
}