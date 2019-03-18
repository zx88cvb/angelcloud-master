package com.angel.provider.model.dto;

import com.angel.base.model.dto.BaseDto;

import java.io.Serializable;

/**
 * 博客评论Dto
 * @Author angel
 * @Date 19-3-18
 */
public class BlogCommentDto extends BaseDto implements Serializable {

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
    private Integer articleId;

    /**
     * 用户id(保留)
     */
    private Integer userId;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 网址
     */
    private String website;

    /**
     * 内容
     */
    private String content;
}
