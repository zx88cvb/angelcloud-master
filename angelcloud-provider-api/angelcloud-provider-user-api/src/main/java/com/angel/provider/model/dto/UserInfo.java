package com.angel.provider.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * user basic info
 * for security
 * @Author angel
 * @Date 2019/12/12
 */
@Data
public class UserInfo implements Serializable {
    /**
     * 用户基本信息
     */
    @ApiModelProperty(value = "用户基本信息")
    private SysUserDto sysUserDto;
    /**
     * 权限标识集合
     */
    @ApiModelProperty(value = "权限标识集合")
    private String[] permissions;
    /**
     * 角色集合
     */
    @ApiModelProperty(value = "角色标识集合")
    private Integer[] roles;
}
