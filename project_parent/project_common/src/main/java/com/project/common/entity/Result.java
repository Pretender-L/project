package com.project.common.entity;


import com.project.common.enums.BaseErrorInfoEnum;

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

    public Result(BaseErrorInfoEnum baseErrorInfoEnum) {
        this.code = baseErrorInfoEnum.getResultCode();
        this.message = baseErrorInfoEnum.getResultMsg();
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
     */
    public static Result success() {
        return success(null);
    }

    /***
     * 成功
     */
    public static Result success(Object data) {
        Result rb = new Result();
        rb.setCode(BaseErrorInfoEnum.SUCCESS.getResultCode());
        rb.setMessage(BaseErrorInfoEnum.SUCCESS.getResultMsg());
        rb.setResult(data);
        return rb;
    }

    /***
     * 成功
     */
    public static Result success(String message, Object data) {
        Result rb = new Result();
        rb.setCode(BaseErrorInfoEnum.SUCCESS.getResultCode());
        rb.setMessage(message);
        rb.setResult(data);
        return rb;
    }

    /**
     * 成功
     */
    public static Result success(String message) {
        Result rb = new Result();
        rb.setCode(BaseErrorInfoEnum.SUCCESS.getResultCode());
        rb.setMessage(message);
        rb.setResult(null);
        return rb;
    }

    /**
     * 失败
     */
    public static Result error(BaseErrorInfoEnum baseErrorInfoEnum) {
        Result rb = new Result();
        rb.setCode(baseErrorInfoEnum.getResultCode());
        rb.setMessage(baseErrorInfoEnum.getResultMsg());
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
