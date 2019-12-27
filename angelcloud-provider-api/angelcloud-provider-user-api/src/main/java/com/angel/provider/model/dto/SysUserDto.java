package com.angel.provider.model.dto;

import com.angel.base.model.domain.BaseEntity;
import com.angel.base.model.dto.BaseDto;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 系统用户表DTO(后台管理)
 * </p>
 *
 * @author angel
 * @since 2018-12-12
 */
@Data
public class SysUserDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户表id
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码，MD5加密
     */
    private String password;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 找回密码问题
     */
    private String question;
    /**
     * 找回密码答案
     */
    private String answer;
    /**
     * 上次登录时间
     */
    private Date lastLoginTime;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 用户状态 0-正常 1-封禁
     */
    private Integer status;

}
