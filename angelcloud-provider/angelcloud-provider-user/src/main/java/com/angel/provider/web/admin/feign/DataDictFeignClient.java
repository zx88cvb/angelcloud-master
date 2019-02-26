package com.angel.provider.web.admin.feign;

import com.angel.base.constant.ResponseCode;
import com.angel.base.constant.ServerResponse;
import com.angel.base.service.ServiceResult;
import com.angel.provider.model.domain.DataDictValue;
import com.angel.provider.model.vo.DataDictValueVo;
import com.angel.provider.service.DataDictFeignApi;
import com.angel.provider.service.IDataDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api("feign远程调用数据字典接口")
public class DataDictFeignClient implements DataDictFeignApi {

    @Resource
    private IDataDictService iDataDictService;

    /**
     * 根据key查询字典值
     * @param key key
     * @return ServerResponse包含list集合
     */
    @Override
    @ApiOperation(value = "根据key值字典数据值", httpMethod = "GET")
    public ServerResponse<List<DataDictValueVo>> getDictValueForKey(@ApiParam(name = "key", value = "key")
                                                                        @PathVariable("key") String key) {
        // 判断参数是否为空
        if (StringUtils.isBlank(key)) {
            return ServerResponse.createByErrorMessage(ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        // 根据key 查询 dictvalue
        ServiceResult<List<DataDictValue>> serverResponse = iDataDictService.getDictValueForKey(key);

        if (!serverResponse.isSuccess()) {
            return ServerResponse.createByErrorMessage(serverResponse.getMessage());
        }

        List<DataDictValue> dataDictValueList = serverResponse.getResult();

        // 转换
        List<DataDictValueVo> dictValueVoList = dataDictValueList.stream().map(e -> {
            DataDictValueVo dataDictValueVo = new DataDictValueVo();
            BeanUtils.copyProperties(e, dataDictValueVo);
            return dataDictValueVo;
        }).collect(Collectors.toList());


        return ServerResponse.createBySuccess(dictValueVoList);
    }

    /**
     * 根据key值和params参数查询字典数据值
     * @param key key
     * @param params 参数
     * @return ServerResponse单个实体
     */
    @Override
    @ApiOperation(value = "根据key值和params参数查询字典数据值", httpMethod = "GET")
    public ServerResponse<DataDictValueVo> getDictValueForKey(@ApiParam(name = "key", value = "key")
                                                                  @PathVariable("key") String key,
                                                              @ApiParam(name = "params", value = "params")
                                                                  @PathVariable("params") String params) {
        // 判断参数是否为空
        if (StringUtils.isBlank(key) || StringUtils.isBlank(params)) {
            return ServerResponse.createByErrorMessage(ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        // 根据key 查询 dictvalue
        ServiceResult<DataDictValue> serverResponse = iDataDictService.getDictValueDetail(key, params);

        if (!serverResponse.isSuccess()) {
            return ServerResponse.createByErrorMessage(serverResponse.getMessage());
        }

        DataDictValue dataDictValue = serverResponse.getResult();

        DataDictValueVo dataDictValueVo = new DataDictValueVo();
        BeanUtils.copyProperties(dataDictValue, dataDictValueVo);

        return ServerResponse.createBySuccess(dataDictValueVo);
    }
}
