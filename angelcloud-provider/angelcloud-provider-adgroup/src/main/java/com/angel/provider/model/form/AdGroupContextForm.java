package com.angel.provider.model.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 广告内容详情表Form
 * @Author angel
 * @Date 19-3-4
 */
@Data
public class AdGroupContextForm implements Serializable {

    private Integer id;

    /**
     * 标题
     */
    @NotNull(message = "标题不能为空!")
    private String title;

    /**
     * 图片路径
     */
    @Size(max = 100, message = "图片路径最大字符不超过100")
    private String imgUrl;

    /**
     * 内容
     */
    @Size(max = 500, message = "内容最大字符不超过500")
    private String content;

    /**
     * 链接地址
     */
    @Size(max = 500, message = "链接最大字符不超过500")
    private String linkUrl;

    /**
     * 打开方式 0:本窗口 1:新窗口
     */
    @NotNull(message = "打开方式不能为空!")
    private Integer target;

    /**
     * 备注
     */
    @Size(max = 500, message = "备注最大字符不超过500")
    private String remake;
}
