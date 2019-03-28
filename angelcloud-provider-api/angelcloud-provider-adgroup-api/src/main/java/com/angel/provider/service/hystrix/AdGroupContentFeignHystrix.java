package com.angel.provider.service.hystrix;

import com.angel.base.constant.ServerResponse;
import com.angel.base.enums.ErrorCodeEnum;
import com.angel.provider.model.vo.AdGroupContentVo;
import com.angel.provider.service.AdGroupContentFeignApi;

import java.util.List;

/**
 * @Author angel
 * @Date 19-3-28
 */
public class AdGroupContentFeignHystrix implements AdGroupContentFeignApi {
    @Override
    public ServerResponse<List<AdGroupContentVo>> getContentByTypeAndGroup(String typeKey, String adKey) {
        return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.GL99990002.code(), ErrorCodeEnum.GL99990002.msg());
    }
}
