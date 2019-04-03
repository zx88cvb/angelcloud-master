package com.angel.provider.model.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 博客评论表Form
 * @Author angel
 * @Date 19-3-18
 */
@Data
@ApiModel("博客评论Form")
public class BlogCommentForm implements Serializable {
    /**
     * 主键id
     */
    @ApiModelProperty("主键id")
    private Integer id;

    /**
     * 父id(默认为0)
     */
    @ApiModelProperty("父id")
    private Integer pId;

    /**
     * 文章id
     */
    @ApiModelProperty("文章id")
    @NotNull(message = "文章id不能为空!")
    private Integer articleId;

    /**
     * 用户id(保留)
     */
    @ApiModelProperty("用户id")
    private Integer userId;

    /**
     * 昵称
     */
    @ApiModelProperty("昵称")
    @NotNull(message = "昵称不能为空!")
    @Size(min = 1, max = 25, message = "昵称字符长度必须在1~25之间")
    private String nickname;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    @Size(min = 1, max = 100, message = "邮箱字符长度必须在1~100之间")
    private String email;

    /**
     * 网址
     */
    @ApiModelProperty("网址")
    @Size(min = 1, max = 100, message = "网址字符长度必须在1~100之间")
    private String website;

    /**
     * 内容
     */
    @ApiModelProperty("内容")
    @Size(min = 1, max = 500, message = "内容字符长度必须在1~500之间")
    private String content;
}
