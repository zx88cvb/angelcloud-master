package com.angel.provider.model.domain;

import com.angel.base.model.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据字典
 */
@Data
public class DataDict extends BaseEntity implements Serializable{
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标识
     */
    private String dictKey;

    /**
     * 描述
     */
    private String description;

}