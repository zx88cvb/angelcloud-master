package com.angel.provider.service.impl;

import com.angel.base.constant.ResponseCode;
import com.angel.base.constant.ServerResponse;
import com.angel.provider.model.vo.DataDictValueVo;
import com.angel.provider.service.DataDictValueFeignApi;
import com.angel.provider.service.IDataDictValueFeignService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: Angel
 * @Date: 2019/02/27.
 * @Description:
 */
@Service
@Slf4j
public class DataDictValueFeignServiceImpl implements IDataDictValueFeignService {

    @Resource
    private DataDictValueFeignApi dataDictValueFeignApi;

    @Override
    public ServerResponse<DataDictValueVo> getDictValueByParams(String params) {
        if (StringUtils.isBlank(params)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),
                    ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        return dataDictValueFeignApi.getDictValueByParams(params);
    }
}
