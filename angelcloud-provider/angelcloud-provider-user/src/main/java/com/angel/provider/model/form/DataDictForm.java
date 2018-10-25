package com.angel.provider.model.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 数据字典Form
 * @Author: Angel
 * @Date: 2018/10/23.
 * @Description:
 */
@Data
public class DataDictForm implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 标识
     */
    @NotNull(message = "标识不能为空!")
    @Size(min = 1, max = 100, message = "标识字符长度必须在1~100之间")
    private String dictKey;

    /**
     * 描述
     */
    @NotNull(message = "描述不能为空!")
    @Size(min = 1, max = 500, message = "描述最大字符不超过500")
    private String description;

    /**
     * 是否删除
     */
    private Integer isDel;
}
