package com.angel.provider.web.controller;


import com.angel.base.constant.ResponseCode;
import com.angel.base.constant.ServerResponse;
import com.angel.base.enums.ErrorCodeEnum;
import com.angel.base.service.ServiceResult;
import com.angel.provider.model.dto.BlogArticleDto;
import com.angel.provider.model.form.BlogArticleForm;
import com.angel.provider.service.IBlogArticleService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 * 博客文章表 前端控制器
 * </p>
 *
 * @author Angel
 * @since 2018-08-24
 */
@RestController
@RequestMapping("/blogArticle")
@Api("博客文章Api")
public class BlogArticleController {

    @Resource
    private IBlogArticleService iBlogArticleService;

    /**
     * 新增博客文章
     * @param request request
     * @param blogArticleForm 博客Form实体类
     * @param bindingResult 验证
     * @return 返回插入个数结果集
     */
    @PostMapping("insertBlogArticle")
    @ApiOperation(value = "新增博客文章", httpMethod = "POST")
    public ServerResponse insertBlogTag (HttpServletRequest request,
                                         @ApiParam(name = "blogArticleForm", value = "博客信息Form") @Valid @RequestBody BlogArticleForm blogArticleForm,
                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ServerResponse.createByErrorMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        BlogArticleDto blogArticleDto = new BlogArticleDto();
        BeanUtils.copyProperties(blogArticleForm, blogArticleDto);

        //TODO 用户id暂时设置成定值
        blogArticleDto.setUserId(1);

        ServiceResult<Integer> integerServiceResult = iBlogArticleService.insertBlogArticle(blogArticleDto);
        if (!integerServiceResult.isSuccess()) {
            return ServerResponse.createByError();
        }

        // 个数小于1时 新增错误
        if (integerServiceResult.getResult() < 1) {
            return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.BLOG10031002.code(),ErrorCodeEnum.BLOG10031002.msg());
        }
        return ServerResponse.createBySuccessMessage(ResponseCode.SUCCESS.getDesc());
    }

    /**
     * 分页条件查询博客文章
     * @param request request
     * @param blogArticleDto 博客DTO实体类
     * @return 返回结果集
     */
    @GetMapping("getBlogArticlePage")
    @ApiOperation(value = "获取博客文章分页数据", httpMethod = "GET")
    public ServerResponse<Page<BlogArticleDto>> getBlogCategoryPage (HttpServletRequest request,
                                                                    @ApiParam(name = "blogArticleDto", value = "博客文章信息DTO")BlogArticleDto blogArticleDto) {
        ServiceResult<Page<BlogArticleDto>> blogArticleDtoResult = iBlogArticleService.getBlogArticlePage(blogArticleDto);
        if (!blogArticleDtoResult.isSuccess()) {
            return ServerResponse.createByError();
        }
        Page<BlogArticleDto> blogArticleDtoPage = blogArticleDtoResult.getResult();
        return ServerResponse.createBySuccess(blogArticleDtoPage);
    }
}

