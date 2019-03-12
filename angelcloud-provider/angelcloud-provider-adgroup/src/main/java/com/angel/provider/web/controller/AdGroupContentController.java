package com.angel.provider.web.controller;

import com.angel.base.constant.GlobalConstant;
import com.angel.base.constant.ResponseCode;
import com.angel.base.constant.ServerResponse;
import com.angel.base.enums.ErrorCodeEnum;
import com.angel.base.service.ServiceResult;
import com.angel.provider.model.domain.AdGroupContent;
import com.angel.provider.model.domain.AdGroupContext;
import com.angel.provider.model.dto.AdGroupContentDto;
import com.angel.provider.model.form.AdGroupContentForm;
import com.angel.provider.model.vo.AdGroupContentVo;
import com.angel.provider.model.vo.AdGroupContextVo;
import com.angel.provider.service.IAdGroupContentService;
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
 * 广告内容 Controller
 * @Author: Angel
 * @Date: 2018/11/4.
 * @Description:
 */
@RestController
@RequestMapping("/ad/content")
@Api("广告内容Api")
public class AdGroupContentController {

    @Resource
    private IAdGroupContentService iAdGroupContentService;

    /**
     * 获取广告内容分页数据
     * @param request request
     * @param adGroupContentDto 分类信息
     * @return 分类集合
     */
    @GetMapping("recent")
    @ApiOperation(value = "获取广告内容分页数据", httpMethod = "GET")
    public ServerResponse<Page<AdGroupContentVo>> recent (HttpServletRequest request,
                                                                          @ApiParam(name = "adGroupContentDto", value = "广告内容信息")
                                                                                  AdGroupContentDto adGroupContentDto) {
        ServiceResult<Page<AdGroupContentVo>> serviceResult = iAdGroupContentService.getAdGroupContentPage(adGroupContentDto);
        if (!serviceResult.isSuccess()) {
            return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.ADGROUP10021011.code(),ErrorCodeEnum.ADGROUP10021011.msg());
        }
        Page<AdGroupContentVo> page = serviceResult.getResult();
        return ServerResponse.createBySuccess(page);
    }

    /**
     * 新增广告内容
     * @param request request
     * @param adGroupContentForm 广告项信息Form
     * @param bindingResult 验证
     * @return 返回code
     */
    @PostMapping("add")
    @ApiOperation(value = "新增广告内容", httpMethod = "POST")
    public ServerResponse add (HttpServletRequest request,
                                             @ApiParam(name = "adGroupContentForm", value = "广告内容信息Form")
                                             @Valid AdGroupContentForm adGroupContentForm,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ServerResponse.createByErrorMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        AdGroupContentDto adGroupContentDto = new AdGroupContentDto();
        BeanUtils.copyProperties(adGroupContentForm, adGroupContentDto);
        ServiceResult<Integer> integerServiceResult = iAdGroupContentService.insertAdGroupContent(adGroupContentDto);
        if (!integerServiceResult.isSuccess()) {
            return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.ADGROUP10021012.code(),ErrorCodeEnum.ADGROUP10021012.msg());
        }
        return ServerResponse.createBySuccessMessage(ResponseCode.SUCCESS.getDesc());
    }

    /**
     * 修改广告内容
     * @param request request
     * @param adGroupContentForm 广告内容Form
     * @param bindingResult 验证
     * @return 返回code
     */
    @PutMapping("edit")
    @ApiOperation(value = "修改广告内容", httpMethod = "PUT")
    public ServerResponse edit (HttpServletRequest request,
                                             @ApiParam(name = "adGroupContentForm", value = "广告内容信息Form")
                                             @Valid AdGroupContentForm adGroupContentForm,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ServerResponse.createByErrorMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        AdGroupContentDto adGroupContentDto = new AdGroupContentDto();
        BeanUtils.copyProperties(adGroupContentForm, adGroupContentDto);

        ServiceResult<Integer> integerServiceResult = iAdGroupContentService.updateAdGroupContent(adGroupContentDto);
        if (!integerServiceResult.isSuccess()) {
            return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.ADGROUP10021013.code(),ErrorCodeEnum.ADGROUP10021013.msg());
        }

        return ServerResponse.createBySuccessMessage(ResponseCode.SUCCESS.getDesc());
    }

    /**
     * 删除广告内容
     * @param request request
     * @param id 主键id
     * @return 返回个数
     */
    @DeleteMapping("delete/{id}")
    @ApiOperation(value = "删除广告内容", httpMethod = "DELETE")
    public ServerResponse delete (HttpServletRequest request,
                                                 @PathVariable(name = "id")
                                                 @ApiParam(name = "id", value = "主键", required = true, type = "int") int id) {
        if (id < GlobalConstant.Attribute.YES) {
            return ServerResponse.createByErrorMessage(ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        ServiceResult<Integer> integerServiceResult = iAdGroupContentService.deleteAdGroupContentById(id);
        // 个数小于1时 删除错误
        if (!integerServiceResult.isSuccess()) {
            return ServerResponse.createByErrorMessage(integerServiceResult.getMessage());
        }

        return ServerResponse.createBySuccessMessage(ResponseCode.SUCCESS.getDesc());
    }

    /**
     * 根据id获取广告内容数据
     * @param request request
     * @param id id
     * @return 单个广告内容详情结果集
     */
    @GetMapping("{id}")
    @ApiOperation(value = "根据id获取广告内容", httpMethod = "GET")
    public ServerResponse<AdGroupContentVo> getById (HttpServletRequest request,
                                                         @ApiParam(name = "id", value = "id") @PathVariable("id") Integer id) {
        // 判断id是否为null 或者小于1
        if (id == null || id < GlobalConstant.Attribute.YES) {
            return ServerResponse.createByErrorMessage(ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        //调用service
        ServiceResult<AdGroupContent> serviceResult = iAdGroupContentService.selectById(id);
        if (!serviceResult.isSuccess()) {
            return ServerResponse.createByErrorMessage(serviceResult.getMessage());
        }

        //获取结果
        AdGroupContent adGroupContent = serviceResult.getResult();

        AdGroupContentVo adGroupContentVo = new AdGroupContentVo();
        BeanUtils.copyProperties(adGroupContent, adGroupContentVo);

        // ContextVo
        AdGroupContextVo adGroupContextVo = new AdGroupContextVo();
        BeanUtils.copyProperties(adGroupContent.getAdGroupContext(), adGroupContextVo);
        adGroupContentVo.setAdGroupContextVo(adGroupContextVo);

        return ServerResponse.createBySuccess(adGroupContentVo);
    }
}
