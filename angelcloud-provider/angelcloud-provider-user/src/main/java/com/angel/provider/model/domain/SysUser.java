package com.angel.provider.model.domain;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 系统用户表(后台管理)
 * </p>
 *
 * @author aa
 * @since 2018-07-30
 */
@Data
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户表id
     */
    @TableId(value = "id", type = IdType.AUTO)
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
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后一次更新时间
     */
    private Date updateTime;
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
    /**
     * 是否删除 0 未删除 1 已删除
     */
    private Integer isDel;

}
