package com.dayup.seckil.repository;

import com.dayup.seckil.model.User;
import com.dayup.seckil.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author domn
 * @version 1.0
 * @date 2020/1/12 23:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserService userService;

    @Test
    public void test() {
        User user = userService.regist(new User("zhangsan", "123456"));
        Assert.assertNotNull(user);
    }

}