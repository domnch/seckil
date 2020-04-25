package com.dayup.seckil.controller;

import com.dayup.seckil.VO.UserVO;
import com.dayup.seckil.model.User;
import com.dayup.seckil.service.UserService;
import com.dayup.seckil.util.MD5Util;
import com.dayup.seckil.util.UUIDUtil;
import com.dayup.seckil.util.ValidateCode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

/**
 * @author domn
 * @version 1.0
 * @date 2020/1/12 23:40
 */
@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toRegister(ModelMap model) {
        model.addAttribute("user", new User());
        return "login";

    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String register(@ModelAttribute(value = "user") @Valid User user, BindingResult bindingResult, HttpSession session,
                           Model model, String code, HttpServletResponse response) {
        if(bindingResult.hasErrors()) {
            return "login";
        }
        String sessionCode = (String) session.getAttribute("code");
        if(!StringUtils.equalsIgnoreCase(code, sessionCode)) {
            logger.info("登录失败，验证码不匹配, username={}", user.getUsername());
            model.addAttribute("message", "验证码不匹配");
            return "login";
        }
        UserVO dbUser = userService.getUser(user.getUsername());
        if(dbUser != null) {
            if(dbUser.getPassword().equals(MD5Util.formToDB(user.getPassword(), dbUser.getDbflag()))){
                // session.setAttribute("user", dbUser);
                String token = UUIDUtil.getUUID();
                userService.saveUserToRedisByToken(dbUser, token);
                Cookie cookie = new Cookie("token", token);
                cookie.setMaxAge(3600);
                cookie.setPath("/");
                response.addCookie(cookie);
                return "redirect:/home";
            } else {
                return "login";
            }

        } else {
            return "login";
        }
    }

    @RequestMapping(value = "/validateCode")
    public String validateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 设置单位的类型格式为图片格式
        response.setContentType("image/jpeg");
        // 禁止图像缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        HttpSession session = request.getSession();

        ValidateCode vCode = new ValidateCode(120, 34, 5, 100);
        session.setAttribute("code", vCode.getCode());
        vCode.write((response.getOutputStream()));
        return null;
    }
}
