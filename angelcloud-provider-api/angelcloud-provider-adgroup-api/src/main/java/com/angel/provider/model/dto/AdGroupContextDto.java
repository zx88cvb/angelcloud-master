package com.angel.provider.model.dto;

import com.angel.base.model.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 广告内容详情表VO
 */
@Data
@ApiModel
public class AdGroupContextDto extends BaseDto implements Serializable {

    @ApiModelProperty("ID")
    private Integer id;

    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String title;

    /**
     * 图片路径
     */
    @ApiModelProperty("图片路径")
    private String imgUrl;

    /**
     * 内容
     */
    @ApiModelProperty("内容")
    private String content;

    /**
     * 链接地址
     */
    @ApiModelProperty("链接地址")
    private String linkUrl;

    /**
     * 打开方式 0:本窗口 1:新窗口
     */
    @ApiModelProperty("打开方式 0:本窗口 1:新窗口")
    private boolean target;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remake;
}