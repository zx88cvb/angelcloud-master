package com.angel.provider.web.admin.controller;

import com.angel.base.constant.GlobalConstant;
import com.angel.base.constant.ResponseCode;
import com.angel.base.constant.ServerResponse;
import com.angel.base.enums.ErrorCodeEnum;
import com.angel.base.service.ServiceResult;
import com.angel.provider.model.domain.DataDictValue;
import com.angel.provider.model.dto.DataDictValueDto;
import com.angel.provider.model.form.DataDictValueForm;
import com.angel.provider.model.vo.DataDictValueVo;
import com.angel.provider.service.IDataDictValueService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @Author: Angel
 * @Date: 2018/10/25.
 * @Description:
 */
@RestController
@RequestMapping("/dict/value")
@Api("数据字典值API")
public class DataDictValueController {

    @Resource
    private IDataDictValueService iDataDictValueService;

    /**
     * 获取数据字典值分页数据
     * @param request request
     * @param dataDictValueDto 字典值信息
     * @return 标签集合
     */
    @GetMapping({"getDataDictValuePage", "recent"})
    @ApiOperation(value = "获取数据字典值分页数据", httpMethod = "GET")
    public ServerResponse<Page<DataDictValueVo>> getDataDictValuePage (HttpServletRequest request,
                                                                       @ApiParam(name = "dataDictValueDto", value = "标签信息")DataDictValueDto dataDictValueDto) {
        ServiceResult<Page<DataDictValueVo>> serviceResult = iDataDictValueService.getDataDictValuePage(dataDictValueDto);
        if (!serviceResult.isSuccess()) {
            return ServerResponse.createByError();
        }
        Page<DataDictValueVo> dataDictValueVoPage = serviceResult.getResult();
        return ServerResponse.createBySuccess(dataDictValueVoPage);
    }

    /**
     * 新增数据字典值
     * @param request request
     * @param dataDictValueForm 数据字典值信息Form
     * @param bindingResult 验证
     * @return 返回code
     */
    @PostMapping({"insertDataDictValue", "add"})
    @ApiOperation(value = "新增数据字典值", httpMethod = "POST")
    public ServerResponse insertDataDictValue (HttpServletRequest request,
                                          @ApiParam(name = "dataDictValueForm", value = "数据字典值信息Form") @Valid DataDictValueForm dataDictValueForm,
                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ServerResponse.createByErrorMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        DataDictValueDto dataDictValueDto = new DataDictValueDto();
        BeanUtils.copyProperties(dataDictValueForm, dataDictValueDto);

        ServiceResult<Integer> integerServiceResult = iDataDictValueService.insertDataDictValue(dataDictValueDto);
        if (!integerServiceResult.isSuccess()) {
            return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.USER10015014.code(),ErrorCodeEnum.USER10015014.msg());
        }
        return ServerResponse.createBySuccessMessage(ResponseCode.SUCCESS.getDesc());
    }

    /**
     * 修改数据字典值
     * @param request request
     * @param dataDictValueForm 数据字典信息Form
     * @param bindingResult 验证
     * @return 返回code
     */
    @PutMapping({"updateDataDictValue", "edit"})
    @ApiOperation(value = "修改数据字典值", httpMethod = "PUT")
    public ServerResponse updateDataDictValue (HttpServletRequest request,
                                          @ApiParam(name = "dataDictValueForm", value = "字典值信息Form") @Valid DataDictValueForm dataDictValueForm,
                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ServerResponse.createByErrorMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        DataDictValueDto dataDictDtoValue = new DataDictValueDto();
        BeanUtils.copyProperties(dataDictValueForm, dataDictDtoValue);

        ServiceResult<Integer> serviceResult = iDataDictValueService.updateDataDictValue(dataDictDtoValue);
        if (!serviceResult.isSuccess()) {
            return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.USER10015015.code(),ErrorCodeEnum.USER10015015.msg());
        }

        return ServerResponse.createBySuccessMessage(ResponseCode.SUCCESS.getDesc());
    }

    /**
     * 删除数据字典值
     * @param request request
     * @param id 主键id
     * @return 返回个数
     */
    @DeleteMapping("delete/{id}")
    @ApiOperation(value = "删除数据字典值", httpMethod = "DELETE")
    public ServerResponse deleteDataDictValue (HttpServletRequest request,
                                          @PathVariable(name = "id") @ApiParam(name = "id", value = "主键", required = true, type = "int") int id) {
        if (id < GlobalConstant.Attribute.YES) {
            return ServerResponse.createByErrorMessage(ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        ServiceResult<Integer> integerServiceResult = iDataDictValueService.deleteDataDictValueById(id);
        // 个数小于1时 删除错误
        if (!integerServiceResult.isSuccess()) {
            return ServerResponse.createByErrorMessage(integerServiceResult.getMessage());
        }

        return ServerResponse.createBySuccessMessage(ResponseCode.SUCCESS.getDesc());
    }
}
