package com.angel.provider.model.vo;

import com.angel.base.model.vo.BaseVo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 博客评论Vo
 * @Author angel
 * @Date 19-3-18
 */
@Data
public class BlogCommentVo extends BaseVo implements Serializable {

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

    /**
     * 子评论集合
     */
    private List<BlogCommentVo> blogCommentList;
}
