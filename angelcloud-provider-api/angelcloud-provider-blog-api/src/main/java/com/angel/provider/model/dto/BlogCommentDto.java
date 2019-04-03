package com.angel.provider.model.dto;

import com.angel.base.model.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 博客评论Dto
 * @Author angel
 * @Date 19-3-18
 */
@ApiModel("博客评论DTO")
@Data
public class BlogCommentDto extends BaseDto implements Serializable {

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
    private String nickname;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 网址
     */
    @ApiModelProperty("网址")
    private String website;

    /**
     * 内容
     */
    @ApiModelProperty("内容")
    private String content;
}
