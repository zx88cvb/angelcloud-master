package com.angel.provider.service;

import com.angel.base.constant.ServerResponse;
import com.angel.provider.model.vo.DataDictValueVo;
import com.angel.provider.service.hystrix.DataDictFeignHystrix;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 数据字典feign
 * @author Angel
 */
@FeignClient(name = "angelcloud-provider-user",fallback = DataDictFeignHystrix.class)
public interface DataDictFeignApi {

    /**
     * 根据key查询字典值
     * @param key key
     * @return ServerResponse包含list集合
     */
    @GetMapping("/api/user/dict/key/{key}")
    ServerResponse<List<DataDictValueVo>> getDictValueForKey(@ApiParam(name = "key", value = "key")
                                                             @PathVariable("key") String key);

    /**
     * 根据key值和params参数查询字典数据值
     * @param key key
     * @param params 参数
     * @return ServerResponse单个实体
     */
    @GetMapping("/api/user/dict/key/{key}/value/{params}")
    ServerResponse<DataDictValueVo> getDictValueForKey(@ApiParam(name = "key", value = "key")
                                                             @PathVariable("key") String key,
                                                             @ApiParam(name = "params", value = "params")
                                                             @PathVariable("params") String params);
}
