package com.angel.provider.model.domain;

import com.angel.base.model.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 广告项表
 */
@Data
public class AdGroupItem extends BaseEntity implements Serializable {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 广告组外键
     */
    private Integer groupId;

    /**
     * 名称
     */
    private String name;

    /**
     * 类型(1 - 文本、2 - 链接、3 - 图片)
     */
    private String type;

    /**
     * 标识
     */
    private String flag;

    /**
     * 备注
     */
    private String remake;

}