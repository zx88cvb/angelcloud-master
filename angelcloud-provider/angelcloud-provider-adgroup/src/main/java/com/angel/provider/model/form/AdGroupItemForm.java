package com.angel.provider.model.form;

import com.angel.base.model.dto.BaseDto;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 广告项表Form
 */
@Data
public class AdGroupItemForm implements Serializable {

    /**
     * id
     */
    private Integer id;

    /**
     * 广告组外键
     */
    @NotNull(message = "广告组项不能为空!")
    private Integer groupId;

    /**
     * 名称
     */
    @NotNull(message = "名称不能为空!")
    @Size(min = 1, max = 50, message = "名称字符长度必须在1~50之间")
    private String name;

    /**
     * 类型(1 - 文本、2 - 链接、3 - 图片)
     */
    @NotNull(message = "类型不能为空!")
    private String type;

    /**
     * 标识
     */
    @NotNull(message = "标识不能为空!")
    @Size(min = 1, max = 50, message = "标识字符长度必须在1~50之间")
    private String flag;

    /**
     * 备注
     */
    @Size(max = 500, message = "备注最大字符不超过500")
    private String remake;

}