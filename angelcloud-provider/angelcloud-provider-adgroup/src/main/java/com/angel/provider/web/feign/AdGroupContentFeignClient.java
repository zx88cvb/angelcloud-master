package com.angel.provider.web.feign;

import com.angel.base.constant.GlobalConstant;
import com.angel.base.constant.ResponseCode;
import com.angel.base.constant.ServerResponse;
import com.angel.base.service.ServiceResult;
import com.angel.provider.model.vo.AdGroupContentVo;
import com.angel.provider.service.AdGroupContentFeignApi;
import com.angel.provider.service.IAdGroupContentService;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * feign广告内容接口
 * @Author angel
 * @Date 19-3-28
 */
@RestController
@Api("feign远程调用广告内容接口")
public class AdGroupContentFeignClient implements AdGroupContentFeignApi {

    @Autowired
    private IAdGroupContentService iAdGroupContentService;

    /**
     * 根据类型key和广告组key 查询广告内容
     * @param typeKey 类型key
     * @param adKey 广告组key
     * @return 结果集
     */
    @Override
    @ApiOperation(httpMethod = "GET", value = "根据类型key和广告组key 查询广告内容")
    public ServerResponse<List<AdGroupContentVo>> getContentByTypeAndGroup(
            @ApiParam(name = "typeKey", value = "类型key", required = true, type = "String")
            @PathVariable("typeKey") String typeKey,
            @ApiParam(name = "adKey", value = "广告组key", required = true, type = "String")
            @PathVariable("adKey") String adKey) {

        // 判断字符串是否为空字符
        if (StringUtils.isBlank(typeKey) || StringUtils.isBlank(adKey)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),
                    ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        // 调用serive 查询
        ServiceResult<List<AdGroupContentVo>> serviceResult =
                iAdGroupContentService.getContentByTypeAndGroup(typeKey, adKey);

        // 是否成功
        if (!serviceResult.isSuccess()) {
            return ServerResponse.createByErrorMessage(serviceResult.getMessage());
        }
        return ServerResponse.createBySuccess(serviceResult.getResult());
    }

    /**
     * 查询主页广告
     * @return map
     */
    public ServerResponse<Map<String, Object>> getAdIndex() {
        Map<String, Object> map = Maps.newHashMap();

        // 调用serive 查询文章类型
        ServiceResult<List<AdGroupContentVo>> resultCategory =
                iAdGroupContentService.getContentByTypeAndGroup(GlobalConstant.Ad.TYPE_KEY.PC_INDEX,
                        GlobalConstant.Ad.AD_KEY.PC_INDEX_LIST_CATEGORY);

        if (!resultCategory.isSuccess()) {
            return ServerResponse.createByError();
        }
        map.put("listCategory", resultCategory.getResult());

        // 调用serive 查询轮播图
        ServiceResult<List<AdGroupContentVo>> serviceResult =
                iAdGroupContentService.getContentByTypeAndGroup(GlobalConstant.Ad.TYPE_KEY.PC_INDEX,
                        GlobalConstant.Ad.AD_KEY.PC_INDEX_LOOP);

        if (!resultCategory.isSuccess()) {
            return ServerResponse.createByError();
        }
        map.put("swiperList", serviceResult.getResult());

        return ServerResponse.createBySuccess(map);
    }

    /**
     * 查询头部菜单
     * @return 结果集
     */
    public ServerResponse<List<AdGroupContentVo>> getAdHead() {
        Map<String, Object> map = Maps.newHashMap();

        // 调用serive 查询头部菜单
        ServiceResult<List<AdGroupContentVo>> resultMenu =
                iAdGroupContentService.getContentByTypeAndGroup(GlobalConstant.Ad.TYPE_KEY.PC_INDEX,
                        GlobalConstant.Ad.AD_KEY.PC_INDEX_TOP_MENU);

        if (!resultMenu.isSuccess()) {
            return ServerResponse.createByError();
        }

        return ServerResponse.createBySuccess(resultMenu.getResult());
    }

    /**
     * 查询友情链接
     * @return 结果集
     */
    public ServerResponse<List<AdGroupContentVo>> getAdLink() {
        Map<String, Object> map = Maps.newHashMap();

        // 调用serive 查询头部菜单
        ServiceResult<List<AdGroupContentVo>> serviceResult =
                iAdGroupContentService.getContentByTypeAndGroup(GlobalConstant.Ad.TYPE_KEY.PC_LINK,
                        GlobalConstant.Ad.AD_KEY.PC_LINK_LIST);

        if (!serviceResult.isSuccess()) {
            return ServerResponse.createByError();
        }

        return ServerResponse.createBySuccess(serviceResult.getResult());
    }
}
