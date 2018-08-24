package com.angel.provider.web.controller;


import com.angel.base.constant.ResponseCode;
import com.angel.base.constant.ServerResponse;
import com.angel.base.enums.ErrorCodeEnum;
import com.angel.base.service.ServiceResult;
import com.angel.provider.model.domain.BlogTag;
import com.angel.provider.model.dto.BlogTagDto;
import com.angel.provider.model.form.BlogTagForm;
import com.angel.provider.model.vo.BlogTagVo;
import com.angel.provider.service.IBlogTagService;
import com.baomidou.mybatisplus.plugins.Page;
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
 * <p>
 * 博客标签表 前端控制器
 * </p>
 *
 * @author Angel
 * @since 2018-08-24
 */
@RestController
@RequestMapping("/blogTag")
@Api("博客标签API")
public class BlogTagController {

    @Resource
    private IBlogTagService iBlogTagService;

    /**
     * 获取博客标签分页数据
     * @param request request
     * @param blogTag 标签信息
     * @return 标签集合
     */
    @GetMapping("getBlogTagPage")
    @ApiOperation(value = "获取博客标签分页数据", httpMethod = "GET")
    public ServerResponse<Page<BlogTagVo>> getBlogTagPage (HttpServletRequest request,
                                                           @ApiParam(name = "blogTag", value = "标签信息")BlogTag blogTag) {
        ServiceResult<Page<BlogTagVo>> blogTagVoResult = iBlogTagService.getBlogTagPage(blogTag);
        if (!blogTagVoResult.isSuccess()) {
            return ServerResponse.createByError();
        }
        Page<BlogTagVo> blogTagVoPage = blogTagVoResult.getResult();
        return ServerResponse.createBySuccess(blogTagVoPage);
    }

    /**
     * 新增博客标签
     * @param request request
     * @param blogTagForm 标签信息Form
     * @param bindingResult 验证
     * @return 返回code
     */
    @PostMapping("insertBlogTag")
    @ApiOperation(value = "新增博客标签", httpMethod = "POST")
    public ServerResponse insertBlogTag (HttpServletRequest request,
                                              @ApiParam(name = "blogTagForm", value = "标签信息Form") @Valid BlogTagForm blogTagForm,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ServerResponse.createByErrorMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        BlogTagDto blogTagDto = new BlogTagDto();
        BeanUtils.copyProperties(blogTagForm, blogTagDto);

        ServiceResult<Integer> integerServiceResult = iBlogTagService.insertBlogTag(blogTagDto);
        if (!integerServiceResult.isSuccess()) {
            return ServerResponse.createByError();
        }

        // 个数小于1时 新增错误
        if (integerServiceResult.getResult() < 1) {
            return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.BLOG10031009.code(),ErrorCodeEnum.BLOG10031009.msg());
        }
        return ServerResponse.createBySuccessMessage(ResponseCode.SUCCESS.getDesc());
    }

    /**
     * 修改博客标签
     * @param request request
     * @param blogTagForm 标签信息Form
     * @param bindingResult 验证
     * @return 返回code
     */
    @PutMapping("updateBlogTag")
    @ApiOperation(value = "修改博客标签", httpMethod = "PUT")
    public ServerResponse updateBlogTag (HttpServletRequest request,
                                              @ApiParam(name = "blogTagForm", value = "分类标签Form") @Valid BlogTagForm blogTagForm,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ServerResponse.createByErrorMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        BlogTagDto blogTagDto = new BlogTagDto();
        BeanUtils.copyProperties(blogTagForm, blogTagDto);

        ServiceResult<Integer> integerServiceResult = iBlogTagService.updateBlogTag(blogTagDto);
        if (!integerServiceResult.isSuccess()) {
            return ServerResponse.createByError();
        }

        // 个数小于1时 新增错误
        if (integerServiceResult.getResult() < 1) {
            return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.BLOG10031010.code(),ErrorCodeEnum.BLOG10031010.msg());
        }

        return ServerResponse.createBySuccessMessage(ResponseCode.SUCCESS.getDesc());
    }

    /**
     * 删除博客标签
     * @param request
     * @param id 主键id
     * @return 返回个数
     */
    @DeleteMapping("deleteBlogTagById/{id}")
    @ApiOperation(value = "删除博客标签", httpMethod = "DELETE")
    public ServerResponse deleteBlogTagById (HttpServletRequest request,
                                                  @PathVariable(name = "id") @ApiParam(name = "id", value = "主键", required = true, type = "int") Integer id) {
        if (id == null || id < 1) {
            return ServerResponse.createByErrorMessage(ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        ServiceResult<Integer> integerServiceResult = iBlogTagService.deleteBlogTagById(id);
        // 个数小于1时 新增错误
        if (integerServiceResult.getResult() < 1) {
            return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.BLOG10031011.code(),ErrorCodeEnum.BLOG10031011.msg());
        }

        return ServerResponse.createBySuccessMessage(ResponseCode.SUCCESS.getDesc());
    }
}

