package com.angel.provider.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * <p>
 * 博客分类表DTO
 * </p>
 *
 * @author Angel
 * @since 2018-08-13
 */
@Data
public class BlogCategoryDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;
    /**
     * 分类名称
     */
    @NotNull(message = "分类信息不能为空!")
    @Size(min = 1, max = 100, message = "分类信息字符长度必须在1~100之间")
    private String categoryName;

    /**
     * 是否删除(0: 未删除 1: 已删除)
     */
    private Integer isDel;

}
