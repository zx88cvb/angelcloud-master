package com.angel.provider.model.form;

import com.angel.base.model.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 广告内容表Form
 */
@Data
public class AdGroupContentForm implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 广告组ID
     */
    @NotNull(message = "广告组不能为空!")
    private Integer groupId;

    /**
     * 广告项ID
     */
    /*@NotNull(message = "广告项不能为空!")*/
    private Integer itemId;

    /**
     * 源ID
     */
    @NotNull(message = "源内容不能为空!")
    private Integer sourceId;

    /**
     * 序号
     */
    private Integer sn;

    /**
     * 备注
     */
    @Size(max = 500, message = "备注最大字符不超过500")
    private String remake;

}