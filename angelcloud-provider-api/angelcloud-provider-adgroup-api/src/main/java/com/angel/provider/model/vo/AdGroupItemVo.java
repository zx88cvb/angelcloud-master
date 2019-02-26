package com.angel.provider.model.vo;

import com.angel.base.model.vo.BaseVo;
import lombok.Data;

import java.io.Serializable;

/**
 * 广告项表Vo
 */
@Data
public class AdGroupItemVo extends BaseVo implements Serializable {

    /**
     * id
     */
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