package com.juix.seckill.common;

import com.juix.seckill.enums.Enums;
import lombok.Data;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-07-21 10:35
 **/
@Data
public class Result<T> {
    // 返回相应状态码
    private Integer status;

    // 返回相应信息
    private String msg;

    // 返回相应数据
    private T data;

    // 不使用
    private String ok;

    public static <T> Result build(Integer status, String msg, T data) {
        return new Result(status, msg, data);
    }

    public static <T> Result ok(T data) {
        return new Result(data);
    }

    public static Result ok() {
        return new Result(null);
    }

    /**
     * 500
     * 服务器内部错误
     * @return
     */
    public static Result errorMsg() {
        return new Result(Enums.SERVER_ERROR.getCode(), Enums.SERVER_ERROR.getMsg(), null);
    }

    /**
     * 501
     * @param data: 不支持的请求
     * @param <T>
     * @return
     */
    public static <T> Result errorMap(T data) {
        return new Result(Enums.SERVER_NOT_IMPLEMENTED.getCode(), Enums.SERVER_NOT_IMPLEMENTED.getMsg(), data);
    }

    /**
     * 503
     * 服务不可用
     * @return
     */
    public static Result errorUnavailable() {
        return new Result(Enums.SERVER_UNAVAILABLE.getCode(), Enums.SERVER_UNAVAILABLE.getMsg(), null);
    }

    /**
     * 555
     * @param msg: 自定义异常
     * @return
     */
    public static Result errorException(String msg) {
        return new Result(Enums.SERVER_EXCEPTION.getCode(), msg, null);
    }


    private Result(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    private Result(T data) {
        this.status = Enums.SUCCESS.getCode();
        this.msg = Enums.SUCCESS.getMsg();
        this.data = data;
    }
}
