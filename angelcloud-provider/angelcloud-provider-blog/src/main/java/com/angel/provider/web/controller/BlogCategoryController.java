package com.angel.provider.web.controller;


import com.angel.base.constant.GlobalConstant;
import com.angel.base.constant.ResponseCode;
import com.angel.base.constant.ServerResponse;
import com.angel.base.enums.ErrorCodeEnum;
import com.angel.base.service.ServiceResult;
import com.angel.provider.model.domain.BlogCategory;
import com.angel.provider.model.dto.BlogCategoryDto;
import com.angel.provider.model.form.BlogCategoryForm;
import com.angel.provider.model.vo.BlogCategoryVo;
import com.angel.provider.service.IBlogCategoryService;
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

/**
 * <p>
 * 博客分类表 前端控制器
 * </p>
 *
 * @author aa
 * @since 2018-08-13
 */
@RestController
@RequestMapping("/blogCategory")
@Api("博客分类API")
public class BlogCategoryController {

    @Resource
    private IBlogCategoryService iBlogCategoryService;


    /**
     * 获取博客分类分页数据
     * @param request request
     * @param blogCategory 分类信息
     * @return 分类集合
     */
    @GetMapping("getBlogCategoryPage")
    @ApiOperation(value = "获取博客分类分页数据", httpMethod = "GET")
    public ServerResponse<Page<BlogCategoryVo>> getBlogCategoryPage (HttpServletRequest request,
                                                                     @ApiParam(name = "blogCategory", value = "分类信息")BlogCategory blogCategory) {
        ServiceResult<Page<BlogCategoryVo>> blogCategoryVoResult = iBlogCategoryService.getBlogCategoryPage(blogCategory);
        if (!blogCategoryVoResult.isSuccess()) {
            return ServerResponse.createByError();
        }
        Page<BlogCategoryVo> blogCategoryVoPage = blogCategoryVoResult.getResult();
        return ServerResponse.createBySuccess(blogCategoryVoPage);
    }

    /**
     * 获取博客分类分页数据
     * @param request request
     * @param blogCategory 分类信息
     * @return 分类集合
     */
    @GetMapping("getBlogCategoryAll")
    @ApiOperation(value = "获取全部博客分类", httpMethod = "GET")
    public ServerResponse<List<BlogCategoryVo>> getBlogCategoryAll (HttpServletRequest request,
                                                                    @ApiParam(name = "blogCategory", value = "分类信息")BlogCategory blogCategory) {
        ServiceResult<List<BlogCategoryVo>> blogCategoryVoResult = iBlogCategoryService.getBlogCategoryAll(blogCategory);
        if (!blogCategoryVoResult.isSuccess()) {
            return ServerResponse.createByError();
        }
        List<BlogCategoryVo> blogCategoryVoPage = blogCategoryVoResult.getResult();
        return ServerResponse.createBySuccess(blogCategoryVoPage);
    }

    /**
     * 新增博客分类
     * @param request request
     * @param blogCategoryForm 分类信息Form
     * @param bindingResult 验证
     * @return 返回code
     */
    @PostMapping("insertBlogCategory")
    @ApiOperation(value = "新增博客分类", httpMethod = "POST")
    public ServerResponse insertBlogCategory (HttpServletRequest request,
                                              @ApiParam(name = "blogCategoryForm", value = "分类信息Form") @Valid BlogCategoryForm blogCategoryForm,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ServerResponse.createByErrorMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        BlogCategoryDto blogCategoryDto = new BlogCategoryDto();
        BeanUtils.copyProperties(blogCategoryForm, blogCategoryDto);
        ServiceResult<Integer> integerServiceResult = iBlogCategoryService.insertBlogCategory(blogCategoryDto);
        if (!integerServiceResult.isSuccess()) {
            return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.BLOG10031006.code(),ErrorCodeEnum.BLOG10031006.msg());
        }
        return ServerResponse.createBySuccessMessage(ResponseCode.SUCCESS.getDesc());
    }

    /**
     * 修改博客分类
     * @param request request
     * @param blogCategoryForm 分类信息Form
     * @param bindingResult 验证
     * @return 返回code
     */
    @PutMapping("updateBlogCategory")
    @ApiOperation(value = "修改博客分类", httpMethod = "PUT")
    public ServerResponse updateBlogCategory (HttpServletRequest request,
                                              @ApiParam(name = "blogCategoryForm", value = "分类信息Form") @Valid BlogCategoryForm blogCategoryForm,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ServerResponse.createByErrorMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        BlogCategoryDto blogCategoryDto = new BlogCategoryDto();
        BeanUtils.copyProperties(blogCategoryForm, blogCategoryDto);

        ServiceResult<Integer> integerServiceResult = iBlogCategoryService.updateBlogCategory(blogCategoryDto);
        if (!integerServiceResult.isSuccess()) {
            return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.BLOG10031007.code(),ErrorCodeEnum.BLOG10031007.msg());
        }

        return ServerResponse.createBySuccessMessage(ResponseCode.SUCCESS.getDesc());
    }

    /**
     * 删除博客分类
     * @param request
     * @param id 主键id
     * @return 返回个数
     */
    @DeleteMapping("deleteBlogCategoryById/{id}")
    @ApiOperation(value = "删除博客分类", httpMethod = "DELETE")
    public ServerResponse deleteBlogCategoryById (HttpServletRequest request,
                                                  @PathVariable(name = "id") @ApiParam(name = "id", value = "主键", required = true, type = "int") int id) {
        if (id < GlobalConstant.Attribute.YES) {
            return ServerResponse.createByErrorMessage(ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        ServiceResult<Integer> integerServiceResult = iBlogCategoryService.deleteBlogCategoryById(id);
        // 个数小于1时 删除错误
        if (!integerServiceResult.isSuccess()) {
            return ServerResponse.createByErrorMessage(integerServiceResult.getMessage());
        }

        return ServerResponse.createBySuccessMessage(ResponseCode.SUCCESS.getDesc());
    }
}

