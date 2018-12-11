package com.angel.provider.web.controller;

import com.angel.base.constant.GlobalConstant;
import com.angel.base.constant.ResponseCode;
import com.angel.base.constant.ServerResponse;
import com.angel.base.enums.ErrorCodeEnum;
import com.angel.base.service.ServiceResult;
import com.angel.provider.model.dto.AdGroupTypeDto;
import com.angel.provider.model.form.AdGroupTypeForm;
import com.angel.provider.model.vo.AdGroupTypeVo;
import com.angel.provider.service.IAdGroupTypeService;
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
 * 广告组分类Controller
 * @Author: Angel
 * @Date: 2018/10/29.
 * @Description:
 */
@RestController
@RequestMapping("/adGroupType")
@Api("广告组分类Api")
public class AdGroupTypeController {

    @Resource
    private IAdGroupTypeService iAdGroupTypeService;

    /**
     * 获取广告组分类分页数据
     * @param request request
     * @param adGroupTypeDto 分类信息
     * @return 分类集合
     */
    @GetMapping("recent")
    @ApiOperation(value = "获取广告组分类分页数据", httpMethod = "GET")
    public ServerResponse<Page<AdGroupTypeVo>> recent (HttpServletRequest request,
                                                                   @ApiParam(name = "adGroupTypeDto", value = "广告组分类信息")AdGroupTypeDto adGroupTypeDto) {
        ServiceResult<Page<AdGroupTypeVo>> serviceResult = iAdGroupTypeService.getAdGroupTypePage(adGroupTypeDto);
        if (!serviceResult.isSuccess()) {
            return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.ADGROUP10021001.code(),ErrorCodeEnum.ADGROUP10021001.msg());
        }
        Page<AdGroupTypeVo> page = serviceResult.getResult();
        return ServerResponse.createBySuccess(page);
    }

    /**
     * 新增广告组分类
     * @param request request
     * @param adGroupTypeForm 广告组分类信息Form
     * @param bindingResult 验证
     * @return 返回code
     */
    @PostMapping("add")
    @ApiOperation(value = "新增广告组分类", httpMethod = "POST")
    public ServerResponse add (HttpServletRequest request,
                                              @ApiParam(name = "adGroupTypeForm", value = "广告组分类信息Form") @Valid AdGroupTypeForm adGroupTypeForm,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ServerResponse.createByErrorMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        AdGroupTypeDto AdGroupTypeDto = new AdGroupTypeDto();
        BeanUtils.copyProperties(adGroupTypeForm, AdGroupTypeDto);
        ServiceResult<Integer> integerServiceResult = iAdGroupTypeService.insertAdGroupType(AdGroupTypeDto);
        if (!integerServiceResult.isSuccess()) {
            return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.ADGROUP10021002.code(),ErrorCodeEnum.ADGROUP10021002.msg());
        }
        return ServerResponse.createBySuccessMessage(ResponseCode.SUCCESS.getDesc());
    }

    /**
     * 修改广告组分类
     * @param request request
     * @param adGroupTypeForm 广告组分类Form
     * @param bindingResult 验证
     * @return 返回code
     */
    @PutMapping("edit")
    @ApiOperation(value = "修改广告组分类", httpMethod = "PUT")
    public ServerResponse edit (HttpServletRequest request,
                                              @ApiParam(name = "adGroupTypeForm", value = "广告组分类信息Form") @Valid AdGroupTypeForm adGroupTypeForm,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ServerResponse.createByErrorMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        AdGroupTypeDto AdGroupTypeDto = new AdGroupTypeDto();
        BeanUtils.copyProperties(adGroupTypeForm, AdGroupTypeDto);

        ServiceResult<Integer> integerServiceResult = iAdGroupTypeService.updateAdGroupType(AdGroupTypeDto);
        if (!integerServiceResult.isSuccess()) {
            return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.ADGROUP10021003.code(),ErrorCodeEnum.ADGROUP10021003.msg());
        }

        return ServerResponse.createBySuccessMessage(ResponseCode.SUCCESS.getDesc());
    }

    /**
     * 删除广告组分类
     * @param request
     * @param id 主键id
     * @return 返回个数
     */
    @DeleteMapping("delete/{id}")
    @ApiOperation(value = "删除广告组分类", httpMethod = "DELETE")
    public ServerResponse delete (HttpServletRequest request,
                                                  @PathVariable(name = "id") @ApiParam(name = "id", value = "主键", required = true, type = "int") int id) {
        if (id < GlobalConstant.Attribute.YES) {
            return ServerResponse.createByErrorMessage(ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        ServiceResult<Integer> integerServiceResult = iAdGroupTypeService.deleteAdGroupTypeById(id);
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
    @GetMapping("type/{id}")
    @ApiOperation(value = "根据id获取广告组分类数据", httpMethod = "GET")
    public ServerResponse<AdGroupTypeDto> getTypeById (HttpServletRequest request,
                                                              @ApiParam(name = "id", value = "id") @PathVariable("id") Integer id) {
        // 判断id是否为null 或者小于1
        if (id == null || id < GlobalConstant.Attribute.YES) {
            return ServerResponse.createByErrorMessage(ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        //调用service
        ServiceResult<AdGroupTypeDto> serviceResult = iAdGroupTypeService.getAdGroupTypeById(id);
        if (!serviceResult.isSuccess()) {
            return ServerResponse.createByErrorMessage(serviceResult.getMessage());
        }

        //获取结果
        AdGroupTypeDto adGroupTypeDto = serviceResult.getResult();

        return ServerResponse.createBySuccess(adGroupTypeDto);
    }
}
