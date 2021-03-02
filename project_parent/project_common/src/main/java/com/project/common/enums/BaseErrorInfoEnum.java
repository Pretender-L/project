package com.project.common.enums;

public enum BaseErrorInfoEnum {
    /**
     * 数据操作错误定义
     */
    SUCCESS("200", "成功!"),
    ERROR("400", "失败"),
    BODY_NOT_MATCH("401", "请求的数据格式不符!"),
    SIGNATURE_NOT_MATCH("402", "请求的数字签名不匹配!"),
    LOGINERROR("403", "用户名或密码错误"),
    NOT_FOUND("404", "未找到该资源!"),
    ACCESSERROR("405", "权限不足"),
    REPERROR("406", "重复操作"),
    INTERNAL_SERVER_ERROR("500", "服务器内部错误!"),
    SERVER_BUSY("501", "服务器正忙，请稍后再试!"),
    REMOTEERROR("502", "远程调用失败");

    /**
     * 错误码
     */
    private String resultCode;
    /**
     * 错误描述
     */
    private String resultMsg;

    BaseErrorInfoEnum(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }
}
