package com.angel.provider.model.vo;

import com.angel.base.model.vo.BaseVo;
import lombok.Data;

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
public class BlogTagVo extends BaseVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 标签名称
     */
    private String tagName;
}
