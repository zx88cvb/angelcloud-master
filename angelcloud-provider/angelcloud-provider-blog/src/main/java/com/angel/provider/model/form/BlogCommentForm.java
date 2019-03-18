package com.angel.provider.model.form;

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
public class BlogCommentForm implements Serializable {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 父id(默认为0)
     */
    private Integer pId;

    /**
     * 文章id
     */
    @NotNull(message = "文章id不能为空!")
    private Integer articleId;

    /**
     * 用户id(保留)
     */
    private Integer userId;

    /**
     * 昵称
     */
    @NotNull(message = "昵称不能为空!")
    @Size(min = 1, max = 25, message = "昵称字符长度必须在1~25之间")
    private String nickname;

    /**
     * 邮箱
     */
    @Size(min = 1, max = 100, message = "邮箱字符长度必须在1~100之间")
    private String email;

    /**
     * 网址
     */
    @Size(min = 1, max = 100, message = "网址字符长度必须在1~100之间")
    private String website;

    /**
     * 内容
     */
    @Size(min = 1, max = 500, message = "内容字符长度必须在1~500之间")
    private String content;
}
