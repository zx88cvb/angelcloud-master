package com.angel.provider.web.controller;


import com.angel.base.constant.GlobalConstant;
import com.angel.base.constant.ResponseCode;
import com.angel.base.constant.ServerResponse;
import com.angel.base.enums.ErrorCodeEnum;
import com.angel.base.service.ServiceResult;
import com.angel.provider.model.dto.BlogArticleDto;
import com.angel.provider.model.form.BlogArticleForm;
import com.angel.provider.model.vo.BlogArticleVo;
import com.angel.provider.service.IBlogArticleService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
//import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/blog/article")
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
    public ServerResponse insertBlogArticle (HttpServletRequest request,
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
    @GetMapping({"getBlogArticlePage", "recent"})
    @ApiOperation(value = "获取博客文章分页数据", httpMethod = "GET")
    public ServerResponse<Page<BlogArticleVo>> getBlogArticlePage (HttpServletRequest request,
                                                                    @ApiParam(name = "blogArticleDto", value = "博客文章信息DTO")BlogArticleDto blogArticleDto) {
        ServiceResult<Page<BlogArticleVo>> blogArticleDtoResult = iBlogArticleService.getBlogArticlePage(blogArticleDto);
        if (!blogArticleDtoResult.isSuccess()) {
            return ServerResponse.createByError();
        }
        Page<BlogArticleVo> blogArticleDtoPage = blogArticleDtoResult.getResult();
        return ServerResponse.createBySuccess(blogArticleDtoPage);
    }

    /**
     * 根据id查询
     * @param request request
     * @param id
     * @return 返回单个对象结果集
     */
    @GetMapping("{id}")
    @ApiOperation(value = "根据id获取博客文章数据", httpMethod = "GET")
    public ServerResponse<BlogArticleVo> getBlogArticleById (HttpServletRequest request,
                                                                     @ApiParam(name = "id", value = "blogArticle文章id") @PathVariable("id") Integer id) {
        // 判断id是否为null 或者小于1
        if (id == null || id < GlobalConstant.Attribute.YES) {
            return ServerResponse.createByErrorMessage(ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        //调用service
        ServiceResult<BlogArticleVo> serviceResult = iBlogArticleService.getBlogArticleById(id);
        if (!serviceResult.isSuccess()) {
            return ServerResponse.createByErrorMessage(serviceResult.getMessage());
        }

        //获取结果
        BlogArticleVo blogArticleVo = serviceResult.getResult();

        return ServerResponse.createBySuccess(blogArticleVo);
    }

    /**
     * 修改博客文章
     * @param request request
     * @param blogArticleForm 博客Form
     * @param bindingResult bindingResult
     * @return 返回修改成功个数结果集
     */
    @PutMapping("updateBlogArticle")
    @ApiOperation(value = "修改博客文章", httpMethod = "PUT")
    public ServerResponse updateBlogArticle (HttpServletRequest request,
                                             @ApiParam(name = "blogArticleForm", value = "博客信息Form") @Valid @RequestBody BlogArticleForm blogArticleForm,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ServerResponse.createByErrorMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        BlogArticleDto blogArticleDto = new BlogArticleDto();
        BeanUtils.copyProperties(blogArticleForm, blogArticleDto);

        //TODO 用户id暂时设置成定值
        blogArticleDto.setUserId(1);

        ServiceResult<Integer> integerServiceResult = iBlogArticleService.updateBlogArticle(blogArticleDto);

        //判断 是否返回错误结果集
        if (!integerServiceResult.isSuccess()) {
            return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.BLOG10031002.code(),ErrorCodeEnum.BLOG10031002.msg());
        }
        return ServerResponse.createBySuccessMessage(ResponseCode.SUCCESS.getDesc());
    }

    /**
     * 删除博客文章
     * @param request request
     * @param id 主键id
     * @return 返回删除个数结果集
     */
    @DeleteMapping("deleteBlogArticleById/{id}")
    @ApiOperation(value = "删除博客文章", httpMethod = "DELETE")
    public ServerResponse deleteBlogArticleById (HttpServletRequest request,
                                                  @PathVariable(name = "id") @ApiParam(name = "id", value = "主键", required = true, type = "int") int id) {
        if (id < 1) {
            return ServerResponse.createByErrorMessage(ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        ServiceResult<Integer> integerServiceResult = iBlogArticleService.deleteBlogArticleById(id);
        // 个数小于1时 删除错误
        if (!integerServiceResult.isSuccess()) {
            return ServerResponse.createByErrorMessage(integerServiceResult.getMessage());
        }

        return ServerResponse.createBySuccessMessage(ResponseCode.SUCCESS.getDesc());
    }
}

