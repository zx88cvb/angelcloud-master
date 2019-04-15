package com.angel.provider.web.controller;

import com.angel.base.constant.GlobalConstant;
import com.angel.base.constant.ResponseCode;
import com.angel.base.constant.ServerResponse;
import com.angel.base.enums.ErrorCodeEnum;
import com.angel.base.service.ServiceResult;
import com.angel.provider.model.domain.BlogComment;
import com.angel.provider.model.dto.BlogCategoryDto;
import com.angel.provider.model.dto.BlogCommentDto;
import com.angel.provider.model.form.BlogCommentForm;
import com.angel.provider.model.vo.BlogArticleVo;
import com.angel.provider.model.vo.BlogCommentVo;
import com.angel.provider.service.IBlogCommentService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 评论Controller
 * @Author angel
 * @Date 19-4-3
 */
@RestController
@RequestMapping("/blog/comment")
@Api(tags = "博客分类API")
public class BlogCommentController {

    @Autowired
    private IBlogCommentService iBlogCommentService;

    /**
     * 获取评论分页数据
     * @param blogCommentDto
     * @return
     */
    @GetMapping("recent")
    @ApiOperation(value = "获取评论数据分页", httpMethod = "GET")
    public ServerResponse recent(@ApiParam(name = "blogArticleDto", value = "评论信息DTO")
                                 BlogCommentDto blogCommentDto) {
        ServiceResult<Page<BlogCommentVo>> serviceResult = iBlogCommentService.getBlogCommentPage(blogCommentDto);
        if (!serviceResult.isSuccess()) {
            return ServerResponse.createByError();
        }
        Page<BlogCommentVo> page = serviceResult.getResult();
        return ServerResponse.createBySuccess(page);
    }

    /**
     * 新增博客评论
     * @param request request
     * @param blogCommentForm 评论Form
     * @param bindingResult 验证
     * @return 返回code
     */
    @PostMapping("add")
    @ApiOperation(value = "新增博客评论", httpMethod = "POST")
    public ServerResponse insertBlogComment (HttpServletRequest request,
                                              @ApiParam(name = "blogCommentForm", value = "评论Form") @Valid BlogCommentForm blogCommentForm,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ServerResponse.createByErrorMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        BlogComment blogComment = new BlogComment();
        BeanUtils.copyProperties(blogCommentForm, blogComment);
        boolean b = iBlogCommentService.save(blogComment);
        if (!b) {
            return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.BLOG10031012.code(),ErrorCodeEnum.BLOG10031012.msg());
        }
        return ServerResponse.createBySuccessMessage(ResponseCode.SUCCESS.getDesc());
    }

    /**
     * 删除博客评论
     * @param request request
     * @param id 主键id
     * @return 返回个数
     */
    @DeleteMapping("delete/{id}")
    @ApiOperation(value = "删除博客评论", httpMethod = "DELETE")
    public ServerResponse deleteBlogCommentById (@ApiIgnore HttpServletRequest request,
                                             @PathVariable(name = "id") @ApiParam(name = "id", value = "主键", required = true, type = "int") int id) {
        if (id < GlobalConstant.Attribute.YES) {
            return ServerResponse.createByErrorMessage(ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        ServiceResult<Integer> integerServiceResult = iBlogCommentService.deleteBlogCommentById(id);
        // 个数小于1时 删除错误
        if (!integerServiceResult.isSuccess()) {
            return ServerResponse.createByErrorMessage(integerServiceResult.getMessage());
        }

        return ServerResponse.createBySuccessMessage(ResponseCode.SUCCESS.getDesc());
    }
}
