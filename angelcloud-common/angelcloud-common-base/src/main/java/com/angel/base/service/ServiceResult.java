package com.angel.base.service;

import com.angel.base.constant.GlobalConstant;
import com.angel.base.enums.ErrorCodeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator on 2018/1/11.
 * 服务通用接口 单个对象
 */
@Getter
@Setter
public class ServiceResult<T> {
    private boolean success;
    private String message;
    private T result;

    public ServiceResult(boolean success) {
        this.success = success;
    }

    public ServiceResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ServiceResult(boolean success, String message, T result) {
        this.success = success;
        this.message = message;
        this.result = result;
    }

    public static <T> ServiceResult<T> success() {
        return new ServiceResult<>(true);
    }

    public static <T> ServiceResult<T> of(T result) {
        ServiceResult<T> serviceResult = new ServiceResult<>(true);
        serviceResult.setResult(result);
        return serviceResult;
    }

    public static <T> ServiceResult<T> notFound() {
        return new ServiceResult<>(false, Message.NOT_FOUND.getValue());
    }

    public static <T> ServiceResult<T> errorByEnumMessage(ErrorCodeEnum errorCodeEnum) {
        return new ServiceResult<>(false, errorCodeEnum.msg());
    }

    public static <T> ServiceResult<T> errorByMessage(String message) {
        return new ServiceResult<>(false, message);
    }

    public enum Message {
        NOT_FOUND("Not Found Resource!"),
        NOT_LOGIN("User not login!");

        private String value;

        Message(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
