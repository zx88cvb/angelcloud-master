package com.angel.provider.service;

import com.angel.base.constant.ServerResponse;
import com.angel.base.constant.ServiceNameConstants;
import com.angel.provider.model.vo.DataDictValueVo;
import com.angel.provider.service.hystrix.DataDictValueFeignHystrix;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 数据字典值feign
 * @author Angel
 */
@FeignClient(contextId = "dataDictValueFeignApi", value = ServiceNameConstants.USER_SERVICE,
        fallback = DataDictValueFeignHystrix.class)
public interface DataDictValueFeignApi {

    /**
     * 根据id查询
     * @param id
     * @return 返回单个对象结果集
     */
    @GetMapping("/api/dictvalue/{id}")
    @ApiOperation(value = "根据id字典数据值", httpMethod = "GET")
    ServerResponse<DataDictValueVo> getById (@ApiParam(name = "id", value = "id") @PathVariable("id") Integer id);

    /**
     * 根据params查询
     * @param params params参数
     * @return 结果集包含单个实体
     */
    @GetMapping("/api/dictvalue/params/{params}")
    @ApiOperation(value = "根据params参数获取字典数据值", httpMethod = "GET")
    ServerResponse<DataDictValueVo> getDictValueByParams(@ApiParam(name = "params", value = "params")
                                                         @PathVariable("params")
                                                                 String params);
}
