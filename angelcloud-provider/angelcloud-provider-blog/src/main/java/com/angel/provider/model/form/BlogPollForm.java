package com.angel.provider.model.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 文章点赞Form
 * @Author angel
 * @Date 19-3-18
 */
@Data
public class BlogPollForm implements Serializable {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 文章id
     */
    @NotNull(message = "文章不能为空!")
    private Integer articleId;

    /**
     * 用户id(保留)
     */
    private Integer userId = 0;

    /**
     * 是否点赞(0:未点赞 1:点赞)
     */
    @NotNull(message = "点赞不能为空!")
    private Integer isPositive;
}
