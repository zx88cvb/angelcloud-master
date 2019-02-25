package com.angel.provider.model.dto;

import com.angel.base.model.dto.BaseDto;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 博客标签表Dto
 * </p>
 *
 * @author Angel
 * @since 2018-08-24
 */
@Data
public class BlogTagDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 标签名称
     */
    private String tagName;
}
