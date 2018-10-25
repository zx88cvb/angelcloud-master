package com.angel.provider.model.vo;

import com.angel.base.model.vo.BaseVo;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Angel
 * @Date: 2018/10/23.
 * @Description:
 */
@Data
public class DataDictVo extends BaseVo implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 标识
     */
    private String dictKey;

    /**
     * 描述
     */
    private String description;
}
