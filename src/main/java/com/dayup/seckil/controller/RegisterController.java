package com.dayup.seckil.controller;

import com.dayup.seckil.model.User;
import com.dayup.seckil.service.UserService;
import com.dayup.seckil.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * @author domn
 * @version 1.0
 * @date 2020/1/12 23:40
 */
@RestController
public class RegisterController {

    private static final Logger logger = LoggerFactory.getLogger(RestController.class);

    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "reg", method = RequestMethod.GET)
    public ModelAndView toRegister(ModelMap model) {
        User user = new User();
        return new ModelAndView("register").addObject(user);
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ModelAndView register(@ModelAttribute(value = "user") @Valid User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new ModelAndView("register");
        }
        logger.info("---------------" + user.getPassword());
        String md5password = MD5Util.formToDB(user.getPassword(), "alex");
        user.setDbflag("alex");
        user.setPassword(md5password);
        user.setId(2018);
        User newUser = userService.regist(user);
        return new ModelAndView("register");
    }

    public static void main(String[] args) {
        String md5password = MD5Util.formToDB("123456", "alex");
        System.out.println(md5password);
    }
}
