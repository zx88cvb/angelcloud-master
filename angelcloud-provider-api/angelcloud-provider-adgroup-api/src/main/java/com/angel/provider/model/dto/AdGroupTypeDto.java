package com.angel.provider.model.dto;

import com.angel.base.model.dto.BaseDto;
import lombok.Data;

import java.io.Serializable;

/**
 * 广告组分类DTO
 */
@Data
public class AdGroupTypeDto extends BaseDto implements Serializable {

    /**
     * id
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 标识
     */
    private String typeKey;

    /**
     * 备注
     */
    private String remake;

}