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
    public Result<T> success() {
        return success((T) null);
    }

    /***
     * 成功
     */
    public Result<T> success(T data) {
        this.setCode(BaseErrorInfoEnum.SUCCESS.getResultCode());
        this.setMessage(BaseErrorInfoEnum.SUCCESS.getResultMsg());
        this.setResult(data);
        return this;
    }

    /***
     * 成功
     */
    public Result<T> success(String message, T data) {
        this.setCode(BaseErrorInfoEnum.SUCCESS.getResultCode());
        this.setMessage(message);
        this.setResult(data);
        return this;
    }

    /**
     * 成功
     */
    public Result<T> success(String message) {
        this.setCode(BaseErrorInfoEnum.SUCCESS.getResultCode());
        this.setMessage(message);
        this.setResult(null);
        return this;
    }

    /**
     * 失败
     */
    public Result<T> error(BaseErrorInfoEnum baseErrorInfoEnum) {
        this.setCode(baseErrorInfoEnum.getResultCode());
        this.setMessage(baseErrorInfoEnum.getResultMsg());
        this.setResult(null);
        return this;
    }

    /**
     * 失败
     */
    public Result<T> error(String code, String message) {
        this.setCode(code);
        this.setMessage(message);
        this.setResult(null);
        return this;
    }

    /**
     * 失败
     */
    public Result<T> error(String message) {
        this.setCode("-1");
        this.setMessage(message);
        this.setResult(null);
        return this;
    }
}
