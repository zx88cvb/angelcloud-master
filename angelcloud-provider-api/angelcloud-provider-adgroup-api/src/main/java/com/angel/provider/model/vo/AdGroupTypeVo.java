package com.angel.provider.model.vo;

import com.angel.base.model.vo.BaseVo;
import lombok.Data;

import java.io.Serializable;

/**
 * 广告组分类Vo
 */
@Data
public class AdGroupTypeVo extends BaseVo implements Serializable {

    /**
     * id
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 标识
     */
    private String typeKey;

    /**
     * 备注
     */
    private String remake;

}