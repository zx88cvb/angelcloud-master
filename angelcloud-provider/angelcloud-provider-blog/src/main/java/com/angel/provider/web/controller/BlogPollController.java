package com.angel.provider.web.controller;

import com.angel.base.constant.ResponseCode;
import com.angel.base.constant.ServerResponse;
import com.angel.base.enums.ErrorCodeEnum;
import com.angel.provider.model.domain.BlogPoll;
import com.angel.provider.model.form.BlogPollForm;
import com.angel.provider.service.IBlogPollService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

/**
 * 点赞Controller
 * @Author angel
 * @Date 19-4-15
 */
@RestController
@RequestMapping("blog/poll")
@Api(tags = "博客点赞API")
public class BlogPollController {

    @Autowired
    private IBlogPollService iBlogPollService;

    /**
     * 新增点赞
     * @param blogPollForm 点赞form
     * @param bindingResult 忽略
     * @return ServerResponse
     */
    @PostMapping("add")
    @ApiOperation(value = "新增博客点赞", httpMethod = "POST")
    public ServerResponse add(@ApiParam(name = "blogCommentForm", value = "点赞Form") @Valid BlogPollForm blogPollForm,
                              @ApiIgnore BindingResult bindingResult) {
        // 验证
        if (bindingResult.hasErrors()) {
            return ServerResponse.createByErrorMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        // Form -> DO
        BlogPoll blogPoll = new BlogPoll();
        BeanUtils.copyProperties(blogPollForm, blogPoll);

        // 新增
        boolean save = iBlogPollService.save(blogPoll);
        if (!save) {
            return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.BLOG10031013.code(),ErrorCodeEnum.BLOG10031013.msg());
        }

        return ServerResponse.createBySuccessMessage(ResponseCode.SUCCESS.getDesc());
    }
}
