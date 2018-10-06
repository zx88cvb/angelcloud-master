package com.angel.provider.model.vo;

import com.angel.base.model.vo.BaseVo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModel;
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
@ApiModel
public class SysUserVo extends BaseVo implements Serializable {

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
     * 头像
     */
    private String avatar;
    /**
     * 用户状态 0-正常 1-封禁
     */
    private Integer status;

}
