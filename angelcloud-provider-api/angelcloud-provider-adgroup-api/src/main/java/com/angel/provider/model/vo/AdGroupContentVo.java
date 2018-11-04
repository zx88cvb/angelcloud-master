package com.angel.provider.model.vo;

import com.angel.base.model.dto.BaseDto;
import com.angel.base.model.vo.BaseVo;
import lombok.Data;

import java.io.Serializable;

/**
 * 广告内容表Vo
 */
@Data
public class AdGroupContentVo extends BaseVo implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 广告组ID
     */
    private Integer groupId;

    /**
     * 广告项ID
     */
    private Integer itemId;

    /**
     * 源ID
     */
    private Integer sourceId;

    /**
     * 备注
     */
    private String remake;

}