package com.angel.provider.service.hystrix;

import com.angel.base.constant.ServerResponse;
import com.angel.base.enums.ErrorCodeEnum;
import com.angel.provider.model.vo.BlogArticleVo;
import com.angel.provider.service.BlogArticleFeignApi;

import java.util.List;

/**
 * @Author angel
 * @Date 19-3-24
 */
public class BlogArticleFeignHystrix implements BlogArticleFeignApi {
    @Override
    public ServerResponse<List<BlogArticleVo>> selectRandArticleThree() {
        return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.GL99990002.code(), ErrorCodeEnum.GL99990002.msg());
    }

    @Override
    public ServerResponse<List<BlogArticleVo>> selectCommentTop(Integer count) {
        return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.GL99990002.code(), ErrorCodeEnum.GL99990002.msg());
    }
}
