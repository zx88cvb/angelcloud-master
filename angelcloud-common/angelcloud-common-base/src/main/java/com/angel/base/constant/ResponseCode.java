package com.angel.base.constant;

/**
 * Created by Administrator on 2017/8/7.
 */
public enum ResponseCode {
    SUCCESS(0,"SUCCESS"),
    ERROR(-1,"ERROR"),
    NEED_LOGIN(-10,"NEED_LOGIN"),
    ILLEGAL_ARGUMENT(-2,"ILLEGAL_ARGUMENT"),
    SERVER_ERROR(500 ,"SERVER_ERROR");

    private final int code;
    private final String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
