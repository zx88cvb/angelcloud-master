package com.angel.provider.model.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * <p>
 * 博客标签表
 * </p>
 *
 * @author Angel
 * @since 2018-08-24
 */
@Data
public class BlogTagForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 标签名称
     */
    @NotNull(message = "分类信息不能为空!")
    @Size(min = 1, max = 100, message = "分类信息字符长度必须在1~100之间")
    private String tagName;
    /**
     * 是否删除(0: 未删除 1: 已删除)
     */
    private Integer isDel;
}
