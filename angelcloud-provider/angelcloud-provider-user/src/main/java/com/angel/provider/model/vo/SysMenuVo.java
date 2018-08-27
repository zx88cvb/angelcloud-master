package com.angel.provider.model.vo;

import com.angel.base.model.vo.BaseVo;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: Angel
 * @Date: 2018/8/20.
 * @Description: 系统菜单Vo
 */

@Data
@ApiModel
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class SysMenuVo extends BaseVo implements Serializable{

    /**
     * id
     */
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
     * 描述
     */
    private String description;
    /**
     * 层级
     */
    private Integer level;
    /**
     * 是否禁用(0:未禁用 1 禁用)
     */
    private Integer isActivity;
    /**
     * 子节点
     */
    private List<SysMenuVo> subMenuList;

}
