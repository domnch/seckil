package com.dayup.seckil.controller.api;

import com.dayup.seckil.VO.UserVO;
import com.dayup.seckil.base.controller.BaseController;
import com.dayup.seckil.base.result.Result;
import com.dayup.seckil.base.result.ResultCode;
import com.dayup.seckil.model.User;
import com.dayup.seckil.service.UserService;
import com.dayup.seckil.util.MD5Util;
import com.dayup.seckil.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author domn
 * @version 1.0
 * @date 2020/1/12 23:40
 */
@RestController
@RequestMapping("/api")
public class LoginApiController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(LoginApiController.class);

    private final UserService userService;

    @Autowired
    public LoginApiController(UserService userService) {
        this.userService = userService;
    }

//    @RequestMapping(value = "/api/login", method = RequestMethod.GET)
//    public Result toRegister(ModelMap model) {
//        model.addAttribute("user", new User());
//        return Result.failure();
//
//    }

    @RequestMapping(value = "/login")
    public Result<Object> register(@ModelAttribute(value = "user") @Valid User user, BindingResult bindingResult, HttpSession session,
                           Model model, String code, HttpServletResponse response) {
        if(bindingResult.hasErrors()) {
            return Result.failure();
        }
//        String sessionCode = (String) session.getAttribute("code");
//        if(!StringUtils.equalsIgnoreCase(code, sessionCode)) {
//            logger.info("登录失败，验证码不匹配, username={}", user.getUsername());
//            model.addAttribute("message", "验证码不匹配");
//            return "login";
//        }
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
                return Result.success();
            } else {
                return Result.failure(ResultCode.USER_LOGIN_ERROR);
            }

        } else {
            return Result.failure(ResultCode.USER_LOGIN_ERROR);
        }
    }

//    @RequestMapping(value = "/validateCode")
//    public String validateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        // 设置单位的类型格式为图片格式
//        response.setContentType("image/jpeg");
//        // 禁止图像缓存
//        response.setHeader("Pragma", "no-cache");
//        response.setHeader("Cache-Control", "no-cache");
//        response.setDateHeader("Expires", 0);
//
//        HttpSession session = request.getSession();
//
//        ValidateCode vCode = new ValidateCode(120, 34, 5, 100);
//        session.setAttribute("code", vCode.getCode());
//        vCode.write((response.getOutputStream()));
//        return null;
//    }
}
