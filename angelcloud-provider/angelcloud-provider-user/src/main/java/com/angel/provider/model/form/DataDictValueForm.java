package com.angel.provider.model.form;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 数据字典值FORM
 * @Author: Angel
 * @Date: 2018/10/25.
 * @Description:
 */
@Data
public class DataDictValueForm implements Serializable {

    /**
     * id
     */
    private Integer id;

    /**
     * 数据字典ID
     */
    @NotNull(message = "数据字典ID不能为空!")
    @Max(value = 11, message = "数据字典ID长度最大为11位")
    private Integer dataDictId;

    /**
     * 显示名称
     */
    @NotNull(message = "显示名称不能为空!")
    @Size(min = 1, max = 100, message = "显示名称字符长度必须在1~100之间")
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
