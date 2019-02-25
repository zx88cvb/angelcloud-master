package com.angel.provider.model.dto;

import com.angel.base.model.dto.BaseDto;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Angel
 * @Date: 2018/10/23.
 * @Description:
 */
@Data
public class DataDictDto extends BaseDto implements Serializable {
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
