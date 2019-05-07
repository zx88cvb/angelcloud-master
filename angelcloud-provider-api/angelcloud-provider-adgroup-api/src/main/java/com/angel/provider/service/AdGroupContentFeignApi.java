package com.angel.provider.service;

import com.angel.base.constant.ServerResponse;
import com.angel.provider.model.vo.AdGroupContentVo;
import com.angel.provider.service.hystrix.AdGroupContentFeignHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

/**
 * 广告内容Feign
 * @Author angel
 * @Date 19-3-28
 */
@FeignClient(name = "angelcloud-provider-adgroup",fallback = AdGroupContentFeignHystrix.class)
public interface AdGroupContentFeignApi {

    /**
     * 根据类型key和广告组key 查询广告内容
     * @param typeKey 类型key
     * @param adKey 广告组key
     * @return 结果集
     */
    @GetMapping("/api/adgroup/{typeKey}/{adKey}/content")
    ServerResponse<List<AdGroupContentVo>> getContentByTypeAndGroup(@PathVariable("typeKey") String typeKey,
                                                            @PathVariable("adKey") String adKey);

    /**
     * 查询主页广告
     * @return map
     */
    @GetMapping("/api/adgroup/index/content")
    ServerResponse<Map<String, Object>> getAdIndex();

    /**
     * 查询头部菜单
     * @return 结果集
     */
    @GetMapping("/api/adgroup/header/content")
    ServerResponse<List<AdGroupContentVo>> getAdHead();

    /**
     * 查询友情链接
     * @return 结果集
     */
    @GetMapping("/api/adgroup/link/content")
    ServerResponse<List<AdGroupContentVo>> getAdLink();
}
