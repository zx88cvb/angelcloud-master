package com.angel.provider.exceptions;

import com.angel.base.enums.ErrorCodeEnum;
import com.angel.base.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Angel
 * @Date: 2018/9/24.
 * @Description:
 */
@Slf4j
public class AdGroupBizException extends BusinessException{

    public AdGroupBizException() {
    }

    public AdGroupBizException(int code, String message) {
        super(code, message);
        log.info("<==== AdGroupBizException, code:{}, message:{}", code, super.getMessage());
    }

    public AdGroupBizException(ErrorCodeEnum codeEnum) {
        super(codeEnum.code(), codeEnum.msg());
        log.info("<==== AdGroupBizException, code:{}, message:{}", this.code, super.getMessage());
    }
}
