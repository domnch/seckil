package com.dayup.seckil.base.result;

/**
 * @author domn
 * @version 1.0
 * @date 2020/3/8 20:20
 */
public class Result<T> {

    private Integer code;

    private String message;

    private T data;

    public static <T> Result<T> failure() {
        Result result = new Result<T>();
        result.setResultCode(ResultCode.FAIL);
        return result;
    }

    public static <T> Result<T> failure(ResultCode resultCode) {
        Result result = new Result<T>();
        result.setResultCode(resultCode);
        return result;
    }

    public static <T> Result<T> failure(ResultCode resultCode, T data) {
        Result<T> result = new Result<T>();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success() {
        Result result = new Result<T>();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    public static <T> Result<T> success(ResultCode resultCode) {
        Result result = new Result<T>();
        result.setResultCode(resultCode);
        return result;
    }

    public static <T> Result<T> success(ResultCode resultCode, T data) {
        Result<T> result = new Result<T>();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    public void setResultCode(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
