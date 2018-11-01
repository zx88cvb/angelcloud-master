package com.angel.provider.model.dto;

import com.angel.base.model.dto.BaseDto;
import lombok.Data;

/**
 * 广告组DTO
 * @Author: Angel
 * @Date: 2018/10/29.
 * @Description:
 */
@Data
public class AdGroupDto extends BaseDto {

    /**
     * id
     */
    private Integer id;

    /**
     * 广告组分类ID
     */
    private Integer typeId;

    /**
     * 名称
     */
    private String name;

    /**
     * 标识
     */
    private String adKey;

    /**
     * 备注
     */
    private String remake;
}
