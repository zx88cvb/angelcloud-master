package com.angel.provider.service.hystrix;

import com.angel.base.constant.ServerResponse;
import com.angel.base.enums.ErrorCodeEnum;
import com.angel.provider.model.vo.DataDictValueVo;
import com.angel.provider.service.DataDictValueFeignApi;

/**
 * @Author: Angel
 * @Date: 2019/02/27.
 * @Description:
 */
public class DataDictValueFeignHystrix implements DataDictValueFeignApi {

    @Override
    public ServerResponse<DataDictValueVo> getById(Integer id) {
        return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.GL99990002.code(), ErrorCodeEnum.GL99990002.msg());
    }

    @Override
    public ServerResponse<DataDictValueVo> getDictValueByParams(String params) {
        return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.GL99990002.code(), ErrorCodeEnum.GL99990002.msg());
    }
}
