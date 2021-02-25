package com.project.excetion;

import com.project.constant.enums.BaseErrorInfoEnum;

public class BadException extends Exception {
    private static final long serialVersionUID = 1L;
    /**
     * 错误码
     */
    protected String errorCode;
    /**
     * 错误信息
     */
    protected String errorMsg;

    public BadException() {
    }

    public BadException(BaseErrorInfoEnum baseErrorInfoEnum) {
        super(baseErrorInfoEnum.getResultCode());
        this.errorCode = baseErrorInfoEnum.getResultCode();
        this.errorMsg = baseErrorInfoEnum.getResultMsg();
    }

    public BadException(BaseErrorInfoEnum baseErrorInfoEnum, Throwable cause) {
        super(baseErrorInfoEnum.getResultCode(), cause);
        this.errorCode = baseErrorInfoEnum.getResultCode();
        this.errorMsg = baseErrorInfoEnum.getResultMsg();
    }

    public BadException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public BadException(String errorCode, String errorMsg) {
        super(errorCode);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BadException(String errorCode, String errorMsg, Throwable cause) {
        super(errorCode, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
