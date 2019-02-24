package com.angel.provider.model.vo;

import com.angel.base.model.vo.BaseVo;
import lombok.Data;

/**
 * 广告组Vo
 * @Author: Angel
 * @Date: 2018/10/29.
 * @Description:
 */
@Data
public class AdGroupVo extends BaseVo {

    /**
     * id
     */
    private Integer id;

    /**
     * 广告组分类ID
     */
    private Integer typeId;

    /**
     * 名称
     */
    private String name;

    /**
     * 标识
     */
    private String adKey;

    /**
     * 备注
     */
    private String remake;

    /**
     * 分组类型
     */
    private AdGroupTypeVo adGroupType;
}
