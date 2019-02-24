package com.angel.provider.model.dto;

import com.angel.base.model.dto.BaseDto;
import lombok.Data;

import java.io.Serializable;

/**
 * 广告内容表Dto
 */
@Data
public class AdGroupContentDto extends BaseDto implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 广告组ID
     */
    private Integer groupId;

    /**
     * 广告项ID
     */
    private Integer itemId;

    /**
     * 源ID
     */
    private Integer sourceId;

    /**
     * 序号
     */
    private Integer sn;

    /**
     * 备注
     */
    private String remake;

}