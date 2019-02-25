package com.angel.provider.model.dto;

import com.angel.base.model.dto.BaseDto;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 博客分类表Dto
 * </p>
 *
 * @author Angel
 * @since 2018-08-13
 */
@Data
public class BlogCategoryDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;
    /**
     * 分类名称
     */
    private String categoryName;

}
