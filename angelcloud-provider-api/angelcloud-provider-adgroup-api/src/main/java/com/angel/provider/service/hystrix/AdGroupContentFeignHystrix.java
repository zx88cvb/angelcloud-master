package com.angel.provider.service.hystrix;

import com.angel.base.constant.ServerResponse;
import com.angel.base.enums.ErrorCodeEnum;
import com.angel.provider.model.vo.AdGroupContentVo;
import com.angel.provider.service.AdGroupContentFeignApi;

import java.util.List;
import java.util.Map;

/**
 * @Author angel
 * @Date 19-3-28
 */
public class AdGroupContentFeignHystrix implements AdGroupContentFeignApi {
    @Override
    public ServerResponse<List<AdGroupContentVo>> getContentByTypeAndGroup(String typeKey, String adKey) {
        return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.GL99990002.code(), ErrorCodeEnum.GL99990002.msg());
    }

    @Override
    public ServerResponse<Map<String, Object>> getAdIndex() {
        return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.GL99990002.code(), ErrorCodeEnum.GL99990002.msg());
    }

    @Override
    public ServerResponse<List<AdGroupContentVo>> getAdHead() {
        return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.GL99990002.code(), ErrorCodeEnum.GL99990002.msg());
    }

    @Override
    public ServerResponse<List<AdGroupContentVo>> getAdLink() {
        return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.GL99990002.code(), ErrorCodeEnum.GL99990002.msg());
    }
}
