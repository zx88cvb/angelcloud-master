package com.angel.base.constant;


/**
 * 全局常量类
 * Created by Administrator on 2018/8/14.
 */
public class GlobalConstant {
    public static final String ROOT_PREFIX = "angelcloud";

    /**
     * 是否删除
     */
    public interface IsDel {
        /**
         * 未删除
         */
        Integer NO = 0;

        /**
         * 已删除
         */
        Integer YES = 1;
    }
}
