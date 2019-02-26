package com.angel.provider.model.dto;

import com.angel.base.model.dto.BaseDto;
import lombok.Data;

import java.io.Serializable;

/**
 * 广告项表
 */
@Data
public class AdGroupItemDto extends BaseDto implements Serializable {

    /**
     * id
     */
    private Integer id;

    /**
     * 广告组外键
     */
    private Integer groupId;

    /**
     * 名称
     */
    private String name;

    /**
     * 类型(1 - 文本、2 - 链接、3 - 图片)
     */
    private String type;

    /**
     * 标识
     */
    private String flag;

    /**
     * 备注
     */
    private String remake;

}