package com.angel.provider.model.vo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 系统用户 Form
 * </p>
 *
 * @author Angel
 * @since 2018-07-30
 */
@Data
public class SysUserVo implements Serializable {

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
     * 昵称
     */
    private String nickname;
    /**
     * 找回密码问题
     */
    private String question;

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
