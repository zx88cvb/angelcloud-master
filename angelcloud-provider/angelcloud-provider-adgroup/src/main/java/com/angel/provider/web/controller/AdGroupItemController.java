package com.angel.provider.web.controller;

import com.angel.base.constant.GlobalConstant;
import com.angel.base.constant.ResponseCode;
import com.angel.base.constant.ServerResponse;
import com.angel.base.enums.ErrorCodeEnum;
import com.angel.base.service.ServiceResult;
import com.angel.provider.model.dto.AdGroupItemDto;
import com.angel.provider.model.form.AdGroupItemForm;
import com.angel.provider.model.vo.AdGroupItemVo;
import com.angel.provider.service.IAdGroupItemService;
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
 * @Date: 2018/11/4.
 * @Description:
 */
@RestController
@RequestMapping("/adGroupItem")
@Api("广告项Api")
public class AdGroupItemController {

    @Resource
    private IAdGroupItemService iAdGroupItemService;

    /**
     * 获取广告项分页数据
     * @param request request
     * @param adGroupItemDto 分类信息
     * @return 分类集合
     */
    @GetMapping({"getAdGroupItemPage", "recent"})
    @ApiOperation(value = "获取广告项分页数据", httpMethod = "GET")
    public ServerResponse<Page<AdGroupItemVo>> recent (HttpServletRequest request,
                                                           @ApiParam(name = "adGroupItemDto", value = "广告项信息")AdGroupItemDto adGroupItemDto) {
        ServiceResult<Page<AdGroupItemVo>> serviceResult = iAdGroupItemService.getAdGroupItemPage(adGroupItemDto);
        if (!serviceResult.isSuccess()) {
            return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.ADGROUP10021008.code(),ErrorCodeEnum.ADGROUP10021008.msg());
        }
        Page<AdGroupItemVo> page = serviceResult.getResult();
        return ServerResponse.createBySuccess(page);
    }

    /**
     * 新增广告项
     * @param request request
     * @param adGroupItemForm 广告项信息Form
     * @param bindingResult 验证
     * @return 返回code
     */
    @PostMapping({"insertAdGroupItem", "add"})
    @ApiOperation(value = "新增广告项", httpMethod = "POST")
    public ServerResponse add (HttpServletRequest request,
                                         @ApiParam(name = "adGroupItemForm", value = "广告项信息Form") @Valid AdGroupItemForm adGroupItemForm,
                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ServerResponse.createByErrorMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        AdGroupItemDto adGroupItemDto = new AdGroupItemDto();
        BeanUtils.copyProperties(adGroupItemForm, adGroupItemDto);
        ServiceResult<Integer> integerServiceResult = iAdGroupItemService.insertAdGroupItem(adGroupItemDto);
        if (!integerServiceResult.isSuccess()) {
            return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.ADGROUP10021009.code(),ErrorCodeEnum.ADGROUP10021009.msg());
        }
        return ServerResponse.createBySuccessMessage(ResponseCode.SUCCESS.getDesc());
    }

    /**
     * 修改广告项分类
     * @param request request
     * @param adGroupItemForm 广告项Form
     * @param bindingResult 验证
     * @return 返回code
     */
    @PutMapping({"updateAdGroupItem", "edit"})
    @ApiOperation(value = "修改广告项", httpMethod = "PUT")
    public ServerResponse edit (HttpServletRequest request,
                                         @ApiParam(name = "adGroupItemForm", value = "广告项信息Form") @Valid AdGroupItemForm adGroupItemForm,
                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ServerResponse.createByErrorMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        AdGroupItemDto adGroupItemDto = new AdGroupItemDto();
        BeanUtils.copyProperties(adGroupItemForm, adGroupItemDto);

        ServiceResult<Integer> integerServiceResult = iAdGroupItemService.updateAdGroupItem(adGroupItemDto);
        if (!integerServiceResult.isSuccess()) {
            return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.ADGROUP10021010.code(),ErrorCodeEnum.ADGROUP10021010.msg());
        }

        return ServerResponse.createBySuccessMessage(ResponseCode.SUCCESS.getDesc());
    }

    /**
     * 删除广告项
     * @param request request
     * @param id 主键id
     * @return 返回个数
     */
    @DeleteMapping({"deleteAdGroupItemById/{id}", "delete/{id}"})
    @ApiOperation(value = "删除广告项", httpMethod = "DELETE")
    public ServerResponse deleteAdGroupItemById (HttpServletRequest request,
                                             @PathVariable(name = "id") @ApiParam(name = "id", value = "主键", required = true, type = "int") int id) {
        if (id < GlobalConstant.Attribute.YES) {
            return ServerResponse.createByErrorMessage(ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        ServiceResult<Integer> integerServiceResult = iAdGroupItemService.deleteAdGroupItemById(id);
        // 个数小于1时 删除错误
        if (!integerServiceResult.isSuccess()) {
            return ServerResponse.createByErrorMessage(integerServiceResult.getMessage());
        }

        return ServerResponse.createBySuccessMessage(ResponseCode.SUCCESS.getDesc());
    }
}
