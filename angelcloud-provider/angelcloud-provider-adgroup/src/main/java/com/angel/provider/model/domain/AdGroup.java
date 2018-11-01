package com.angel.provider.model.domain;

import com.angel.base.model.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 广告组表
 */
@Data
public class AdGroup extends BaseEntity implements Serializable {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
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