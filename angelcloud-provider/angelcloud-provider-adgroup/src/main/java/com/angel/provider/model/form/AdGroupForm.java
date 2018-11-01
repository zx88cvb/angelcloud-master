package com.angel.provider.model.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 广告组Form
 * @Author: Angel
 * @Date: 2018/10/29.
 * @Description:
 */
@Data
public class AdGroupForm {

    /**
     * id
     */
    private Integer id;

    /**
     * 广告组分类ID
     */
    @NotNull(message = "广告组分类不能为空!")
    private Integer typeId;

    /**
     * 名称
     */
    @NotNull(message = "名称不能为空!")
    @Size(min = 1, max = 50, message = "名称字符长度必须在1~50之间")
    private String name;

    /**
     * 标识
     */
    @NotNull(message = "标识不能为空!")
    @Size(min = 1, max = 50, message = "标识字符长度必须在1~50之间")
    private String adKey;

    /**
     * 备注
     */
    @Size(max = 500, message = "文章摘录最大字符不超过500")
    private String remake;
}
