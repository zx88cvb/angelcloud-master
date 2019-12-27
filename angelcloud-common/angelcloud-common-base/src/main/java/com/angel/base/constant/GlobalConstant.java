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

    /**
     * 属性
     */
    public interface Attribute {
        /**
         * 0
         */
        Integer NO = 0;

        /**
         * 1
         */
        Integer YES = 1;
    }

    /**
     * The constant FILE_MAX_SIZE.
     */
    public static final long FILE_MAX_SIZE = 5 * 1024 * 1024;
    public static final int M_SIZE = 1024;

    public static final String UNKNOWN = "unknown";

    public static final String X_FORWARDED_FOR = "X-Forwarded-For";
    public static final String X_REAL_IP = "X-Real-IP";
    public static final String PROXY_CLIENT_IP = "Proxy-Client-IP";
    public static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
    public static final String HTTP_CLIENT_IP = "HTTP_CLIENT_IP";
    public static final String HTTP_X_FORWARDED_FOR = "HTTP_X_FORWARDED_FOR";

    public static final String DEV_PROFILE = "dev";
    public static final String TEST_PROFILE = "test";
    public static final String PRO_PROFILE = "pro";

    public static final int EXCEPTION_CAUSE_MAX_LENGTH = 2048;
    public static final int EXCEPTION_MESSAGE_MAX_LENGTH = 2048;

    /**
     * 系统常量
     */
    public static final class Sys {

        private Sys() {
        }

        /**
         * 全局用户名
         */
        public static final String TOKEN_AUTH_DTO = "CURRENT_USER_DTO";

        /**
         * 超级管理员的用户ID
         */
        public static final Long SUPER_MANAGER_USER_ID = 1L;
        /**
         * 超级管理员的用户编号
         */
        public static final String SUPER_MANAGER_LOGIN_NAME = "admin";
        /**
         * 超级管理员角色ID
         */
        public static final Long SUPER_MANAGER_ROLE_ID = 1L;
        /**
         * 超级管理员组织ID
         */
        public static final Long SUPER_MANAGER_GROUP_ID = 1L;
        /**
         * 运营工作台ID
         */
        public static final Long OPER_APPLICATION_ID = 1L;

        /**
         * The constant MENU_ROOT.
         */
        public static final String MENU_ROOT = "root";

        /**
         * redis key default expire = 1MINUTES
         */
        public static final long REDIS_DEFAULT_EXPIRE = 1L;

        /**
         * 删除
         */
        public static final Integer STATUS_DEL = 1;
        /**
         * 正常
         */
        public static final Integer STATUS_NORMAL = 0;

        /**
         * 锁定
         */
        public static final Integer STATUS_LOCK = 1;

        /**
         * 菜单
         */
        public static final Integer MENU = 0;

        /**
         * 菜单树根节点
         */
        public static final Integer MENU_TREE_ROOT_ID = -1;

        /**
         * 编码
         */
        public static final String UTF8 = "UTF-8";
    }

    public interface Ad {
        /**
         * 类型键
         */
        interface TYPE_KEY {
            /**
             * index主页
             */
            String PC_INDEX = "pc_index";

            /**
             * 友情链接
             */
            String PC_LINK = "pc_link";
        }

        /**
         * 广告键
         */
        interface AD_KEY {
            /**
             * 轮播图
             */
            String PC_INDEX_LOOP = "pc_index_loop";

            /**
             * 主页头部菜单
             */
            String PC_INDEX_TOP_MENU = "pc_index_top_menu";

            /**
             * 主页文章类型
             */
            String PC_INDEX_LIST_CATEGORY = "pc_index_list_category";

            /**
             * 友情链接
             */
            String PC_LINK_LIST = "pc_link_list";

        }
    }
}
