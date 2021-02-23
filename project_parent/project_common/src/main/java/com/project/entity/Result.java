package com.project.entity;

import com.project.excetion.service.BaseErrorInfo;
import com.project.excetion.service.impl.BaseErrorInfoEnumImpl;

/**
 * 返回结果实体类
 */
public class Result<T> {
    /**
     * 响应代码
     */
    private String code;
    /**
     * 响应消息
     */
    private String message;
    /**
     * 响应结果
     */
    private T result;

    public Result() {
    }

    public Result(BaseErrorInfo errorInfo) {
        this.code = errorInfo.getResultCode();
        this.message = errorInfo.getResultMsg();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    /***
     * 成功
     * @return
     */
    public static Result success() {
        return success(null);
    }

    /***
     * 成功
     * @param data
     * @return
     */
    public static Result success(Object data) {
        Result rb = new Result();
        rb.setCode(BaseErrorInfoEnumImpl.SUCCESS.getResultCode());
        rb.setMessage(BaseErrorInfoEnumImpl.SUCCESS.getResultMsg());
        rb.setResult(data);
        return rb;
    }

    /***
     * 成功
     * @param data
     * @return
     */
    public static Result success(String message, Object data) {
        Result rb = new Result();
        rb.setCode(BaseErrorInfoEnumImpl.SUCCESS.getResultCode());
        rb.setMessage(message);
        rb.setResult(data);
        return rb;
    }

    /**
     * 成功
     */
    public static Result success(String message) {
        Result rb = new Result();
        rb.setCode("BaseErrorInfoEnumImpl.SUCCESS.getResultCode()");
        rb.setMessage(message);
        rb.setResult(null);
        return rb;
    }

    /**
     * 失败
     */
    public static Result error(BaseErrorInfo errorInfo) {
        Result rb = new Result();
        rb.setCode(errorInfo.getResultCode());
        rb.setMessage(errorInfo.getResultMsg());
        rb.setResult(null);
        return rb;
    }

    /**
     * 失败
     */
    public static Result error(String code, String message) {
        Result rb = new Result();
        rb.setCode(code);
        rb.setMessage(message);
        rb.setResult(null);
        return rb;
    }

    /**
     * 失败
     */
    public static Result error(String message) {
        Result rb = new Result();
        rb.setCode("-1");
        rb.setMessage(message);
        rb.setResult(null);
        return rb;
    }
}
