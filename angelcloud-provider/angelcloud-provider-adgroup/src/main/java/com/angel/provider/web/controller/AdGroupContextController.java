package com.angel.provider.web.controller;

import com.angel.base.constant.GlobalConstant;
import com.angel.base.constant.ResponseCode;
import com.angel.base.constant.ServerResponse;
import com.angel.base.enums.ErrorCodeEnum;
import com.angel.base.service.ServiceResult;
import com.angel.provider.model.domain.AdGroupContext;
import com.angel.provider.model.dto.AdGroupContextDto;
import com.angel.provider.model.form.AdGroupContextForm;
import com.angel.provider.model.vo.AdGroupContextVo;
import com.angel.provider.service.IAdGroupContextService;
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
import java.util.List;
import java.util.stream.Collectors;

/**
 * 广告内容详情Controller
 * @Author angel
 * @Date 19-3-4
 */
@RestController
@RequestMapping("/ad/context")
@Api("广告内容详情Api")
public class AdGroupContextController {

    @Resource
    private IAdGroupContextService iAdGroupContextService;

    /**
     * 获取广告内容详情分页数据
     * @param request request
     * @param adGroupContextDto 广告内容详情信息
     * @return 分类集合
     */
    @GetMapping("recent")
    @ApiOperation(value = "获取广告内容详情分页数据", httpMethod = "GET")
    public ServerResponse<Page<AdGroupContextVo>> recent (HttpServletRequest request,
                                                          @ApiParam(name = "adGroupContentDto", value = "广告内容详情信息")
                                                                  AdGroupContextDto adGroupContextDto) {
        // 查询
        ServiceResult<Page<AdGroupContext>> serviceResult = iAdGroupContextService.getConditionPage(adGroupContextDto);

        // 判断是否成功
        if (!serviceResult.isSuccess()) {
            return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.ADGROUP10021015.code(),
                    ErrorCodeEnum.ADGROUP10021015.msg());
        }
        Page<AdGroupContext> page = serviceResult.getResult();
        Page<AdGroupContextVo> pageVo = new Page<>();

        // 转换Vo
        List<AdGroupContextVo> adGroupContextVoList = page.getRecords().stream().map(e -> {
            AdGroupContextVo adGroupContextVo = new AdGroupContextVo();
            BeanUtils.copyProperties(e, adGroupContextVo);
            return adGroupContextVo;
        }).collect(Collectors.toList());

        BeanUtils.copyProperties(page, pageVo);
        pageVo.setRecords(adGroupContextVoList);


        return ServerResponse.createBySuccess(pageVo);
    }

    /**
     * 新增广告内容详情
     * @param request request
     * @param adGroupContextForm 广告项信息详情Form
     * @param bindingResult 验证
     * @return 返回code
     */
    @PostMapping("add")
    @ApiOperation(value = "新增广告内容详情", httpMethod = "POST")
    public ServerResponse add (HttpServletRequest request,
                               @ApiParam(name = "adGroupContextForm", value = "广告内容详情信息Form")
                               @Valid AdGroupContextForm adGroupContextForm,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ServerResponse.createByErrorMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        AdGroupContextDto adGroupContextDto = new AdGroupContextDto();
        BeanUtils.copyProperties(adGroupContextForm, adGroupContextDto);
        ServiceResult<Integer> integerServiceResult = iAdGroupContextService.insert(adGroupContextDto);
        if (!integerServiceResult.isSuccess()) {
            return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.ADGROUP10021016.code(),
                    ErrorCodeEnum.ADGROUP10021016.msg());
        }
        return ServerResponse.createBySuccessMessage(ResponseCode.SUCCESS.getDesc());
    }

    /**
     * 修改广告内容
     * @param request request
     * @param adGroupContextForm 广告内容详情Form
     * @param bindingResult 验证
     * @return 返回code
     */
    @PutMapping("edit")
    @ApiOperation(value = "修改广告内容详情", httpMethod = "PUT")
    public ServerResponse edit (HttpServletRequest request,
                                @ApiParam(name = "adGroupContextForm", value = "广告内容详情信息Form")
                                @Valid AdGroupContextForm adGroupContextForm,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ServerResponse.createByErrorMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        AdGroupContextDto adGroupContextDto = new AdGroupContextDto();
        BeanUtils.copyProperties(adGroupContextForm, adGroupContextDto);

        ServiceResult<Integer> integerServiceResult = iAdGroupContextService.update(adGroupContextDto);
        if (!integerServiceResult.isSuccess()) {
            return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.ADGROUP10021017.code(),ErrorCodeEnum.ADGROUP10021017.msg());
        }

        return ServerResponse.createBySuccessMessage(ResponseCode.SUCCESS.getDesc());
    }

    /**
     * 删除广告内容详情
     * @param request request
     * @param id 主键id
     * @return 返回个数
     */
    @DeleteMapping("delete/{id}")
    @ApiOperation(value = "删除广告内容详情", httpMethod = "DELETE")
    public ServerResponse delete (HttpServletRequest request,
                                  @PathVariable(name = "id")
                                  @ApiParam(name = "id", value = "主键", required = true, type = "int") int id) {
        if (id < GlobalConstant.Attribute.YES) {
            return ServerResponse.createByErrorMessage(ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        ServiceResult<Integer> integerServiceResult = iAdGroupContextService.deleteById(id);
        // 个数小于1时 删除错误
        if (!integerServiceResult.isSuccess()) {
            return ServerResponse.createByErrorMessage(integerServiceResult.getMessage());
        }

        return ServerResponse.createBySuccessMessage(ResponseCode.SUCCESS.getDesc());
    }

    /**
     * 根据id获取广告内容详情数据
     * @param request request
     * @param id id
     * @return 单个广告内容详情结果集
     */
    @GetMapping("{id}")
    @ApiOperation(value = "根据id获取广告内容详情", httpMethod = "GET")
    public ServerResponse<AdGroupContextVo> getTypeById (HttpServletRequest request,
                                                      @ApiParam(name = "id", value = "id") @PathVariable("id") Integer id) {
        // 判断id是否为null 或者小于1
        if (id == null || id < GlobalConstant.Attribute.YES) {
            return ServerResponse.createByErrorMessage(ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        //调用service
        ServiceResult<AdGroupContext> serviceResult = iAdGroupContextService.selectById(id);
        if (!serviceResult.isSuccess()) {
            return ServerResponse.createByErrorMessage(serviceResult.getMessage());
        }

        //获取结果
        AdGroupContext adGroupContext = serviceResult.getResult();

        AdGroupContextVo adGroupContextVo = new AdGroupContextVo();
        BeanUtils.copyProperties(adGroupContext, adGroupContextVo);

        return ServerResponse.createBySuccess(adGroupContextVo);
    }
}
