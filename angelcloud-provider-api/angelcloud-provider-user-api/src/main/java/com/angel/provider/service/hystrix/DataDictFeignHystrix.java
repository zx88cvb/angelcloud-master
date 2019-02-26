package com.angel.provider.service.hystrix;

import com.angel.base.constant.ServerResponse;
import com.angel.base.enums.ErrorCodeEnum;
import com.angel.provider.model.vo.DataDictValueVo;
import com.angel.provider.service.DataDictFeignApi;

import java.util.List;

/**
 * @Author: Angel
 * @Date: 2019/02/26.
 * @Description:
 */
public class DataDictFeignHystrix implements DataDictFeignApi {

    @Override
    public ServerResponse<List<DataDictValueVo>> getDictValueForKey(String key) {
        return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.GL99990002.code(), ErrorCodeEnum.GL99990002.msg());
    }

    @Override
    public ServerResponse<DataDictValueVo> getDictValueForKey(String key, String params) {
        return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.GL99990002.code(), ErrorCodeEnum.GL99990002.msg());
    }
}
