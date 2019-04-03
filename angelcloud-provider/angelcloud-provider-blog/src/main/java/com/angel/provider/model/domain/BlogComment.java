package com.angel.provider.model.domain;

import com.angel.base.model.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 博客评论表
 * </p>
 *
 * @author Angel
 * @since 2019-03-18
 */
@Data
public class BlogComment extends BaseEntity implements Serializable {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
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