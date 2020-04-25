package com.dayup.seckil.base.result;

/**
 * @author domn
 * @version 1.0
 * @date 2020/3/8 20:26
 */
public enum ResultCode {

    SUCCESS(200, "成功"),
    FAIL(500, "失败"),
    USER_LOGIN_ERROR(500201, "登录失败， 用户名或密码出错，请重新输入")
    ;

    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
