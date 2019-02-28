package com.angel.provider.web.admin.feign;

import com.angel.base.constant.GlobalConstant;
import com.angel.base.constant.ResponseCode;
import com.angel.base.constant.ServerResponse;
import com.angel.base.service.ServiceResult;
import com.angel.provider.model.domain.DataDictValue;
import com.angel.provider.model.vo.DataDictValueVo;
import com.angel.provider.service.DataDictValueFeignApi;
import com.angel.provider.service.IDataDictValueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api("feign远程调用数据字典值接口")
public class DataDictValueFeignClient implements DataDictValueFeignApi {

    @Resource
    private IDataDictValueService iDataDictValueService;

    /**
     * 根据id查询
     * @param id
     * @return 返回单个对象结果集
     */
    @Override
    @ApiOperation(value = "根据key值字典数据值", httpMethod = "GET")
    public ServerResponse<DataDictValueVo> getById(@ApiParam(name = "id", value = "id")
                                                       @PathVariable("id")Integer id) {
        // 判断id是否为null 或者小于1
        if (id == null || id < GlobalConstant.Attribute.YES) {
            return ServerResponse.createByErrorMessage(ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        //调用service
        DataDictValue dataDictValue = iDataDictValueService.getById(id);
        /*if (!serviceResult.isSuccess()) {
            return ServerResponse.createByErrorMessage(serviceResult.getMessage());
        }*/

        //获取结果
        DataDictValueVo dataDictValueVo = new DataDictValueVo();
        BeanUtils.copyProperties(dataDictValue, dataDictValueVo);

        return ServerResponse.createBySuccess(dataDictValueVo);
    }

    /**
     * 根据params查询
     * @param params params参数
     * @return 结果集包含单个实体
     */
    @Override
    @ApiOperation(value = "根据params参数获取字典数据值", httpMethod = "GET")
    public ServerResponse<DataDictValueVo> getDictValueByParams(@ApiParam(name = "params", value = "params")
                                                                @PathVariable("params")
                                                                            String params) {
        if (StringUtils.isBlank(params)) {
            return ServerResponse.createByErrorMessage(ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        // 查询
        ServiceResult<DataDictValue> serviceResult = iDataDictValueService.getEntityByParams(params);
        if (!serviceResult.isSuccess()) {
            return ServerResponse.createByErrorMessage(serviceResult.getMessage());
        }

        // 赋值
        DataDictValue dataDictValue = serviceResult.getResult();
        DataDictValueVo dataDictValueVo = new DataDictValueVo();
        BeanUtils.copyProperties(dataDictValue, dataDictValueVo);
        return ServerResponse.createBySuccess(dataDictValueVo);
    }
}
