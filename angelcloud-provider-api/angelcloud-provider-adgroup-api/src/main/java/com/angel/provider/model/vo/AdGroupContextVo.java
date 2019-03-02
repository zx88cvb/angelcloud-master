package com.angel.provider.model.vo;

import com.angel.base.model.domain.BaseEntity;
import com.angel.base.model.vo.BaseVo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 广告内容详情表VO
 */
@Data
public class AdGroupContextVo extends BaseVo implements Serializable {

    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 图片路径
     */
    private String imgUrl;

    /**
     * 内容
     */
    private String content;

    /**
     * 链接地址
     */
    private String linkUrl;

    /**
     * 打开方式 0:本窗口 1:新窗口
     */
    private Integer target;

    /**
     * 备注
     */
    private String remake;
}