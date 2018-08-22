package com.angel.provider.model.vo;

import com.angel.base.model.domain.BaseEntity;
import com.angel.base.model.vo.BaseVo;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 博客分类表Vo
 * </p>
 *
 * @author Angel
 * @since 2018-08-13
 */
@Data
public class BlogCategoryVo extends BaseVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 分类名称
     */
    private String categoryName;

}
