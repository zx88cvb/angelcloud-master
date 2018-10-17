package com.angel.provider.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 异常信息
 */
@Data
public class SysExceptionLog implements Serializable {
    /**
     * id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 系统应用名
     */
    private String applicationName;

    /**
     * 异常类型
     */
    private String exceptionSimpleName;

    /**
     * 异常信息
     */
    private String exceptionMessage;

    /**
     * 异常原因
     */
    private String exceptionCause;

    /**
     * 操作者姓名
     */
    private String creator;

    /**
     * 操作者id
     */
    private Integer creatorId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 异常堆栈信息
     */
    private String exceptionStack;

}