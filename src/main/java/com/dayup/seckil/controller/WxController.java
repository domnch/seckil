package com.dayup.seckil.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


/**
 * @author domn
 * @version 1.0
 * @date 2020/1/20 0:02
 */
@RequestMapping("/wx")
@Controller
public class WxController {

    @GetMapping("/callback")
    public void handleCallback(HttpServletResponse response, String signature, String timestamp, String nonce, String echostr) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.write(echostr);
    }
}
