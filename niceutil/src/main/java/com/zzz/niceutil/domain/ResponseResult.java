package com.zzz.niceutil.domain;

import lombok.Data;

/**
 * response 返回
 */
@Data
public class ResponseResult<T> {

    protected Integer code;
    protected String msg;
    protected T data;

    public ResponseResult() {
    }

    public ResponseResult(T data, String msg, Integer code) {
        this.msg = msg;
        this.data = data;
        this.code = code;
    }

    public static <T> ResponseResult<T> success(String msg) {
        return new ResponseResult(true, msg, 1);
    }

    public static <T> ResponseResult<T> success(String msg, T data) {
        return new ResponseResult<>(data, msg, 1);
    }

    public static <T> ResponseResult<T> success(String msg, T data, Integer code) {
        return new ResponseResult<>(data, msg, code);
    }

    public static <T> ResponseResult<T> error(String msg) {
        return new ResponseResult(false, msg, 0);
    }

    public static <T> ResponseResult<T> error(String msg, T data) {
        return new ResponseResult<>(data, msg, 0);
    }

    public static <T> ResponseResult<T> error(String msg, T data, Integer code) {
        return new ResponseResult<>(data, msg, code);
    }
}
