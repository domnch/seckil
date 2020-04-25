package com.dayup.seckil.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author domn
 * @version 1.0
 * @date 2020/1/18 1:56
 */
public class MD5Util {

    private static final String salt = "springboot";

    public static String md5(String src) {
        return DigestUtils.md2Hex(src);
    }

    // 对应前端第一次加密
    public static String inputToForm(String inputPass) {
        String str = inputPass + salt;
        return md5(str);
    }

    // 对应第二次加密
    public static String formToDB(String formPass, String dbSalt) {
        String str = dbSalt + formPass;
        return md5(str);
    }

    public static String inputToDB(String inputPass, String dbSalt) {
        String formPass = inputToForm(inputPass);
        String bdPass = formToDB(formPass, dbSalt);
        return bdPass;
    }

    public static void main(String[] args) {
        System.out.println(inputToDB("123456", "alex"));
    }


}
