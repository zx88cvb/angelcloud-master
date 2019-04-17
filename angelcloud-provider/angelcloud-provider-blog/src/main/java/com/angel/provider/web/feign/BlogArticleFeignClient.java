package com.angel.provider.web.feign;

import com.angel.base.constant.ResponseCode;
import com.angel.base.constant.ServerResponse;
import com.angel.base.service.ServiceResult;
import com.angel.provider.model.vo.BlogArticleVo;
import com.angel.provider.service.BlogArticleFeignApi;
import com.angel.provider.service.IBlogArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 博客文章feign
 * @Author angel
 * @Date 19-3-24
 */
@RestController
@Api("feign远程调用博客文章接口")
public class BlogArticleFeignClient implements BlogArticleFeignApi {

    @Autowired
    private IBlogArticleService iBlogArticleService;

    /**
     * 随机查询3篇文章
     * @return
     */
    @Override
    @ApiOperation(httpMethod = "GET", value = "随机查询3篇文章")
    public ServerResponse<List<BlogArticleVo>> selectRandArticleThree() {
        ServiceResult<List<BlogArticleVo>> serviceResult = iBlogArticleService.selectRandArticleThree();
        if (!serviceResult.isSuccess()) {
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess(serviceResult.getResult());
    }

    /**
     * 根据评论多少查询
     * @param count 查询个数
     * @return 集合
     */
    @Override
    @ApiOperation(httpMethod = "GET", value = "根据评论多少查询")
    public ServerResponse<List<BlogArticleVo>> selectCommentTop(@ApiParam(name = "count", value = "查询个数", required = true, type = "String")
                                                                          @PathVariable("count")
                                                                                  Integer count) {
        ServiceResult<List<BlogArticleVo>> serviceResult = iBlogArticleService.selectCommentTop(count);
        if (!serviceResult.isSuccess()) {
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess(serviceResult.getResult());
    }
}
