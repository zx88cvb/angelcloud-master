package com.angel.provider.web.admin.controller;

import com.angel.base.constant.GlobalConstant;
import com.angel.base.constant.ResponseCode;
import com.angel.base.constant.ServerResponse;
import com.angel.base.enums.ErrorCodeEnum;
import com.angel.base.service.ServiceResult;
import com.angel.provider.model.domain.DataDict;
import com.angel.provider.model.domain.DataDictValue;
import com.angel.provider.model.dto.DataDictDto;
import com.angel.provider.model.form.DataDictForm;
import com.angel.provider.model.vo.DataDictValueVo;
import com.angel.provider.model.vo.DataDictVo;
import com.angel.provider.service.IDataDictService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Angel
 * @Date: 2018/10/23.
 * @Description:
 */
@RestController
@RequestMapping("/dict")
@Api("数据字典API")
public class DataDictController {

    @Resource
    private IDataDictService iDataDictService;

    /**
     * 获取数据字典分页数据
     * @param request request
     * @param dataDictDto 字典信息
     * @return 标签集合
     */
    @GetMapping({"getDataDictPage", "recent"})
    @ApiOperation(value = "获取数据字典分页数据", httpMethod = "GET")
    public ServerResponse<Page<DataDictVo>> getDataDictPage (HttpServletRequest request,
                                                            @ApiParam(name = "dataDictDto", value = "字典信息")DataDictDto dataDictDto) {
        ServiceResult<Page<DataDictVo>> serviceResult = iDataDictService.getDataDictPage(dataDictDto);
        if (!serviceResult.isSuccess()) {
            return ServerResponse.createByError();
        }
        Page<DataDictVo> dataDictVoPage = serviceResult.getResult();
        return ServerResponse.createBySuccess(dataDictVoPage);
    }

    /**
     * 新增数据字典
     * @param request request
     * @param dataDictForm 数据字典信息Form
     * @param bindingResult 验证
     * @return 返回code
     */
    @PostMapping({"insertDataDict", "add"})
    @ApiOperation(value = "新增数据字典", httpMethod = "POST")
    public ServerResponse insertDataDict (HttpServletRequest request,
                                         @ApiParam(name = "dataDictForm", value = "字典信息Form") @Valid DataDictForm dataDictForm,
                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ServerResponse.createByErrorMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        DataDictDto dataDictDto = new DataDictDto();
        BeanUtils.copyProperties(dataDictForm, dataDictDto);

        ServiceResult<Integer> integerServiceResult = iDataDictService.insertDataDict(dataDictDto);
        if (!integerServiceResult.isSuccess()) {
            return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.USER10015013.code(),ErrorCodeEnum.USER10015013.msg());
        }
        return ServerResponse.createBySuccessMessage(ResponseCode.SUCCESS.getDesc());
    }

    /**
     * 修改数据字典
     * @param request request
     * @param dataDictForm 数据字典信息Form
     * @param bindingResult 验证
     * @return 返回code
     */
    @PutMapping({"updateDataDict", "edit"})
    @ApiOperation(value = "修改数据字典", httpMethod = "PUT")
    public ServerResponse updateDataDict (HttpServletRequest request,
                                              @ApiParam(name = "dataDictForm", value = "字典信息Form") @Valid DataDictForm dataDictForm,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ServerResponse.createByErrorMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        DataDictDto dataDictDto = new DataDictDto();
        BeanUtils.copyProperties(dataDictForm, dataDictDto);

        ServiceResult<Integer> serviceResult = iDataDictService.updateDataDict(dataDictDto);
        if (!serviceResult.isSuccess()) {
            return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.USER10015011.code(),ErrorCodeEnum.USER10015011.msg());
        }

        return ServerResponse.createBySuccessMessage(ResponseCode.SUCCESS.getDesc());
    }

    /**
     * 删除数据字典
     * @param request
     * @param id 主键id
     * @return 返回个数
     */
    @DeleteMapping("delete/{id}")
    @ApiOperation(value = "删除数据字典", httpMethod = "DELETE")
    public ServerResponse deleteDataDict (HttpServletRequest request,
                                             @PathVariable(name = "id") @ApiParam(name = "id", value = "主键", required = true, type = "int") int id) {
        if (id < GlobalConstant.Attribute.YES) {
            return ServerResponse.createByErrorMessage(ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        ServiceResult<Integer> integerServiceResult = iDataDictService.deleteDataDictById(id);
        // 个数小于1时 删除错误
        if (!integerServiceResult.isSuccess()) {
            return ServerResponse.createByErrorMessage(integerServiceResult.getMessage());
        }

        return ServerResponse.createBySuccessMessage(ResponseCode.SUCCESS.getDesc());
    }

    /**
     * 根据id查询
     * @param request request
     * @param id
     * @return 返回单个对象结果集
     */
    @GetMapping("{id}")
    @ApiOperation(value = "根据id字典数据", httpMethod = "GET")
    public ServerResponse<DataDictVo> getById (HttpServletRequest request,
                                                   @ApiParam(name = "id", value = "id") @PathVariable("id") Integer id) {
        // 判断id是否为null 或者小于1
        if (id == null || id < GlobalConstant.Attribute.YES) {
            return ServerResponse.createByErrorMessage(ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        //调用service
        DataDict dataDict = iDataDictService.getById(id);
        /*if (!serviceResult.isSuccess()) {
            return ServerResponse.createByErrorMessage(serviceResult.getMessage());
        }*/

        //获取结果
        DataDictVo dataDictVo = new DataDictVo();
        BeanUtils.copyProperties(dataDict, dataDictVo);

        return ServerResponse.createBySuccess(dataDictVo);
    }

    /**
     * 根据key查询字典值
     * @param request request
     * @param key key
     * @return ServerResponse包含list集合
     */
    /*@GetMapping("key/{key}")
    @ApiOperation(value = "根据key值字典数据值", httpMethod = "GET")
    public ServerResponse<List<DataDictValueVo>> getDictValueForKey(HttpServletRequest request,
                                                              @ApiParam(name = "key", value = "key")
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
    }*/
}
