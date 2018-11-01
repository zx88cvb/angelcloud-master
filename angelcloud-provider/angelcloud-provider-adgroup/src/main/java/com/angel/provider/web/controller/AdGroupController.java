package com.angel.provider.web.controller;

import com.angel.base.constant.GlobalConstant;
import com.angel.base.constant.ResponseCode;
import com.angel.base.constant.ServerResponse;
import com.angel.base.enums.ErrorCodeEnum;
import com.angel.base.service.ServiceResult;
import com.angel.provider.model.dto.AdGroupDto;
import com.angel.provider.model.dto.AdGroupTypeDto;
import com.angel.provider.model.form.AdGroupForm;
import com.angel.provider.model.vo.AdGroupTypeVo;
import com.angel.provider.model.vo.AdGroupVo;
import com.angel.provider.service.IAdGroupService;
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
 * 广告组Controller
 * @Author: Angel
 * @Date: 2018/11/1.
 * @Description:
 */
@RestController
@RequestMapping("/adGroup")
@Api("广告组Api")
public class AdGroupController {

    @Resource
    private IAdGroupService iAdGroupService;

    /**
     * 获取广告组分页数据
     * @param request request
     * @param adGroupDto 分类信息
     * @return 分类集合
     */
    @GetMapping("getAdGroupPage")
    @ApiOperation(value = "获取广告组分页数据", httpMethod = "GET")
    public ServerResponse<Page<AdGroupVo>> getAdGroupPage (HttpServletRequest request,
                                                               @ApiParam(name = "adGroupDto", value = "广告组信息")AdGroupDto adGroupDto) {
        ServiceResult<Page<AdGroupVo>> serviceResult = iAdGroupService.getAdGroupPage(adGroupDto);
        if (!serviceResult.isSuccess()) {
            return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.ADGROUP10021005.code(),ErrorCodeEnum.ADGROUP10021005.msg());
        }
        Page<AdGroupVo> page = serviceResult.getResult();
        return ServerResponse.createBySuccess(page);
    }

    /**
     * 新增广告组
     * @param request request
     * @param adGroupForm 广告组信息Form
     * @param bindingResult 验证
     * @return 返回code
     */
    @PostMapping("insertAdGroup")
    @ApiOperation(value = "新增广告组", httpMethod = "POST")
    public ServerResponse insertAdGroup (HttpServletRequest request,
                                             @ApiParam(name = "adGroupForm", value = "广告组信息Form") @Valid AdGroupForm adGroupForm,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ServerResponse.createByErrorMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        AdGroupDto AdGroupDto = new AdGroupDto();
        BeanUtils.copyProperties(adGroupForm, AdGroupDto);
        ServiceResult<Integer> integerServiceResult = iAdGroupService.insertAdGroup(AdGroupDto);
        if (!integerServiceResult.isSuccess()) {
            return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.ADGROUP10021006.code(),ErrorCodeEnum.ADGROUP10021006.msg());
        }
        return ServerResponse.createBySuccessMessage(ResponseCode.SUCCESS.getDesc());
    }

    /**
     * 修改广告组分类
     * @param request request
     * @param adGroupForm 广告组Form
     * @param bindingResult 验证
     * @return 返回code
     */
    @PutMapping("updateAdGroup")
    @ApiOperation(value = "修改广告组", httpMethod = "PUT")
    public ServerResponse updateAdGroup (HttpServletRequest request,
                                             @ApiParam(name = "adGroupForm", value = "广告组信息Form") @Valid AdGroupForm adGroupForm,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ServerResponse.createByErrorMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        AdGroupDto AdGroupDto = new AdGroupDto();
        BeanUtils.copyProperties(adGroupForm, AdGroupDto);

        ServiceResult<Integer> integerServiceResult = iAdGroupService.updateAdGroup(AdGroupDto);
        if (!integerServiceResult.isSuccess()) {
            return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.ADGROUP10021007.code(),ErrorCodeEnum.ADGROUP10021007.msg());
        }

        return ServerResponse.createBySuccessMessage(ResponseCode.SUCCESS.getDesc());
    }

    /**
     * 删除广告组
     * @param request request
     * @param id 主键id
     * @return 返回个数
     */
    @DeleteMapping("deleteAdGroupById/{id}")
    @ApiOperation(value = "删除广告组", httpMethod = "DELETE")
    public ServerResponse deleteAdGroupById (HttpServletRequest request,
                                                 @PathVariable(name = "id") @ApiParam(name = "id", value = "主键", required = true, type = "int") int id) {
        if (id < GlobalConstant.Attribute.YES) {
            return ServerResponse.createByErrorMessage(ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        ServiceResult<Integer> integerServiceResult = iAdGroupService.deleteAdGroupById(id);
        // 个数小于1时 删除错误
        if (!integerServiceResult.isSuccess()) {
            return ServerResponse.createByErrorMessage(integerServiceResult.getMessage());
        }

        return ServerResponse.createBySuccessMessage(ResponseCode.SUCCESS.getDesc());
    }
}
