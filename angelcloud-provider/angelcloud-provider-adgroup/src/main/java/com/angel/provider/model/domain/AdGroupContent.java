package com.angel.provider.model.domain;

import com.angel.base.model.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 广告内容表
 */
@Data
public class AdGroupContent extends BaseEntity implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
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