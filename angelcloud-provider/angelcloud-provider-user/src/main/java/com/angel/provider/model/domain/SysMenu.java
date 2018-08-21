package com.angel.provider.model.domain;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 后台菜单表
 * </p>
 *
 * @author aa
 * @since 2018-08-18
 */
@Data
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 父id
     */
    private Integer pId;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * url链接
     */
    private String url;
    /**
     * 图标
     */
    private String iconImg;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后一次更新时间
     */
    private Date updateTime;
    /**
     * 排序
     */
    private Integer orderIndex;
    /**
     * 层级
     */
    private Integer level;
    /**
     * 是否禁用(0:未禁用 1 禁用)
     */
    private Integer isActivity;
    /**
     * 是否删除(0:未删除 1:删除)
     */
    private Integer isDel;

}
