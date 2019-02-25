package com.angel.provider.model.dto;

import com.angel.base.model.dto.BaseDto;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Angel
 * @Date: 2018/10/25.
 * @Description:
 */
@Data
public class DataDictValueDto extends BaseDto implements Serializable {
    /**
     * id
     */
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
