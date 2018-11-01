package com.angel.provider.model.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 广告组分类Form
 */
@Data
public class AdGroupTypeForm implements Serializable {

    /**
     * id
     */
    private Integer id;

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
    private String typeKey;

    /**
     * 备注
     */
    private String remake;

}