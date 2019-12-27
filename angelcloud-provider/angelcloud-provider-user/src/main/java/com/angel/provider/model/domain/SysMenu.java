package com.angel.provider.model.domain;

import com.angel.base.model.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class SysMenu extends BaseEntity implements Serializable {

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
     * 权限名称
     */
    private String permission;
    /**
     * url链接
     */
    private String url;
    /**
     * 图标
     */
    private String iconImg;
    /**
     * 描述
     */
    private String description;
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

}
