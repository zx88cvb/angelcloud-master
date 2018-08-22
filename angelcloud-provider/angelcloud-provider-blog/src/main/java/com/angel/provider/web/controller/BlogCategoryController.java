package com.angel.provider.web.controller;


import com.angel.base.constant.ServerResponse;
import com.angel.base.service.ServiceResult;
import com.angel.provider.model.domain.BlogCategory;
import com.angel.provider.model.vo.BlogCategoryVo;
import com.angel.provider.service.IBlogCategoryService;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
}

