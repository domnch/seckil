package com.dayup.seckil.service;

import com.dayup.seckil.VO.UserVO;
import com.dayup.seckil.model.User;
import com.dayup.seckil.util.MD5Util;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author domn
 * @version 1.0
 * @date 2020/1/21 20:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    public UserService userService;

    @Test
    public void regist() {
        String password = MD5Util.inputToDB("123456", "alet");
        User user = new User("wangwu", password);
        user.setDbflag("alet");
        user.setId(1);
        Assert.assertNotNull(userService.regist(user));
    }

    @Test
    public void getUser() {
        UserVO user = userService.getUser("wangwu");
        String password = MD5Util.inputToDB("123456", user.getDbflag());
        Assert.assertEquals(password, user.getPassword());
    }
}