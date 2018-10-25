package com.angel.provider.model.domain;

import com.angel.base.model.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据字典值表
 */
@Data
public class DataDictValue extends BaseEntity implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 数据字典ID
     */
    private Integer dataDictId;

    /**
     * 显示名称
     */
    private String showValue;

    /**
     * 排序
     */
    private Integer sn;

    /**
     * 参数(JSON字符串格式)
     */
    private String params;

}