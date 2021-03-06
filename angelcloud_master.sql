/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : angelcloud_master

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 12/05/2019 17:04:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ad_group
-- ----------------------------
DROP TABLE IF EXISTS `ad_group`;
CREATE TABLE `ad_group`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type_id` int(11) NOT NULL COMMENT '广告组分类ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `ad_key` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标识',
  `remake` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_del` int(2) NOT NULL DEFAULT 0 COMMENT '是否删除(0: 未删除 1: 已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '广告组表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ad_group_content
-- ----------------------------
DROP TABLE IF EXISTS `ad_group_content`;
CREATE TABLE `ad_group_content`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `group_id` int(11) NOT NULL DEFAULT 0 COMMENT '广告组ID',
  `item_id` int(11) NOT NULL DEFAULT 0 COMMENT '广告项ID',
  `source_id` int(11) NOT NULL DEFAULT 0 COMMENT '源ID',
  `sn` int(11) NOT NULL DEFAULT 0 COMMENT '序号',
  `remake` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_del` int(2) NOT NULL DEFAULT 0 COMMENT '是否删除(0: 未删除 1: 已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '广告内容表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ad_group_context
-- ----------------------------
DROP TABLE IF EXISTS `ad_group_context`;
CREATE TABLE `ad_group_context`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `img_url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片链接',
  `content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '内容',
  `link_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '链接',
  `target` int(1) NOT NULL DEFAULT 0 COMMENT '打开方式(0:本窗口 1:新窗口)',
  `remake` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_del` int(2) NOT NULL DEFAULT 0 COMMENT '是否删除(0: 未删除 1: 已删除)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IDX_A_G_C_ID`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '广告内容表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ad_group_item
-- ----------------------------
DROP TABLE IF EXISTS `ad_group_item`;
CREATE TABLE `ad_group_item`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `group_id` int(11) NULL DEFAULT NULL COMMENT '广告组外键',
  `name` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `type` int(11) NOT NULL COMMENT '类型(1 - 文本、2 - 链接、3 - 图片)',
  `flag` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标识',
  `remake` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_del` int(2) NOT NULL DEFAULT 0 COMMENT '是否删除(0: 未删除 1: 已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '广告项表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ad_group_type
-- ----------------------------
DROP TABLE IF EXISTS `ad_group_type`;
CREATE TABLE `ad_group_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `type_key` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标识',
  `remake` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_del` int(2) NOT NULL DEFAULT 0 COMMENT '是否删除(0: 未删除 1: 已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '广告组分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ad_image
-- ----------------------------
DROP TABLE IF EXISTS `ad_image`;
CREATE TABLE `ad_image`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `TITLE` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `IMAGE_URL` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片地址',
  `URL` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接',
  `TARGET` int(11) NULL DEFAULT 1 COMMENT '打开方式 1-本窗口打开， 2- 新窗口打开，默认1',
  `REMARK` varchar(600) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) NOT NULL COMMENT '更新时间',
  `IS_DEL` int(11) NOT NULL DEFAULT 0 COMMENT '数据是否删除。0:未删除;1:已删除',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '图片内容表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for blog_article
-- ----------------------------
DROP TABLE IF EXISTS `blog_article`;
CREATE TABLE `blog_article`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `author` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '作者',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文章标题',
  `excerpt` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文章摘录(描述)',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文章内容',
  `blog_status` int(2) NOT NULL DEFAULT 0 COMMENT '文章状态(0: 私有 1: 开放)',
  `comment_status` int(2) NOT NULL DEFAULT 0 COMMENT '评论状态 (0: 关闭 1 开放)',
  `thumbnail` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '缩略图',
  `order_index` int(11) NULL DEFAULT 0 COMMENT '排序',
  `is_recommend` int(2) NULL DEFAULT 0 COMMENT '是否被推荐(0 未推荐 1 推荐)',
  `user_id` int(11) NOT NULL COMMENT '发表用户id',
  `category_id` int(11) NOT NULL COMMENT '分类id',
  `browse_count` int(11) NULL DEFAULT 0 COMMENT '浏览量',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_del` int(2) NOT NULL DEFAULT 0 COMMENT '是否删除(0: 未删除 1: 已删除)',
  `link_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '外链',
  `source` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '来源',
  `post_time` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IDX_B_A_ID`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '博客文章表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for blog_article_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog_article_tag`;
CREATE TABLE `blog_article_tag`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` int(11) NOT NULL COMMENT '文章id',
  `tag_id` int(11) NOT NULL COMMENT '标签id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IDX_B_A_T_ID`(`id`, `article_id`, `tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章表 标签表 关联表(多对多)' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for blog_category
-- ----------------------------
DROP TABLE IF EXISTS `blog_category`;
CREATE TABLE `blog_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分类名称',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_del` int(2) NOT NULL DEFAULT 0 COMMENT '是否删除(0: 未删除 1: 已删除)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IDX_B_C_ID`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '博客分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for blog_comment
-- ----------------------------
DROP TABLE IF EXISTS `blog_comment`;
CREATE TABLE `blog_comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `p_id` int(11) NOT NULL DEFAULT 0 COMMENT '父id 默认为0',
  `article_id` int(11) NOT NULL DEFAULT 0 COMMENT '文章ID',
  `user_id` int(11) NOT NULL DEFAULT 0 COMMENT '用户ID(保留)',
  `nickname` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '昵称',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `website` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '网址',
  `content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '评论内容',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_del` int(2) NOT NULL DEFAULT 0 COMMENT '是否删除(0: 未删除 1: 已删除)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IDX_B_C_ID`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for blog_poll
-- ----------------------------
DROP TABLE IF EXISTS `blog_poll`;
CREATE TABLE `blog_poll`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `article_id` int(11) NOT NULL DEFAULT 0 COMMENT '文章ID',
  `user_id` int(11) NOT NULL DEFAULT 0 COMMENT '用户ID',
  `is_positive` int(1) NOT NULL DEFAULT 0 COMMENT '是否点赞',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_del` int(2) NOT NULL DEFAULT 0 COMMENT '是否删除(0: 未删除 1: 已删除)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IDX_B_P_ID`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章点赞表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for blog_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog_tag`;
CREATE TABLE `blog_tag`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标签名称',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_del` int(2) NOT NULL DEFAULT 0 COMMENT '是否删除(0: 未删除 1: 已删除)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IDX_B_T_ID`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '博客标签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for data_dict
-- ----------------------------
DROP TABLE IF EXISTS `data_dict`;
CREATE TABLE `data_dict`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dict_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标识',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '描述',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_del` int(2) NOT NULL DEFAULT 0 COMMENT '数据是否删除。0:未删除;1:已删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IDX_D_D_DEL`(`is_del`) USING BTREE,
  INDEX `IDX_D_D_KEY`(`dict_key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for data_dict_value
-- ----------------------------
DROP TABLE IF EXISTS `data_dict_value`;
CREATE TABLE `data_dict_value`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `data_dict_id` int(11) NOT NULL COMMENT '数据字典ID',
  `show_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '显示名称',
  `sn` int(11) NOT NULL COMMENT '排序',
  `params` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '参数(JSON字符串格式)',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_del` int(2) NOT NULL DEFAULT 0 COMMENT '数据是否删除。0:未删除;1:已删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IDX_D_D_V_DEL`(`is_del`) USING BTREE,
  INDEX `IDX_D_D_V_D_D_ID`(`data_dict_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据字典值表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_exception_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_exception_log`;
CREATE TABLE `sys_exception_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `application_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '系统应用名',
  `exception_simple_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '异常类型',
  `exception_message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '异常信息(通过exception.getMessage()获取到的内容)',
  `exception_cause` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '异常原因(通过exception.getCause()获取到的内容)',
  `exception_stack` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '异常堆栈信息',
  `creator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作者姓名',
  `creator_id` int(11) NULL DEFAULT NULL COMMENT '操作者id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IDX_S_E_L_ID`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2770 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '异常信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `p_id` int(11) NOT NULL DEFAULT 0 COMMENT '父id',
  `menu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'url链接',
  `icon_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `order_index` int(2) NOT NULL DEFAULT 0 COMMENT '排序',
  `level` int(2) NOT NULL DEFAULT 0 COMMENT '层级',
  `is_activity` int(2) NOT NULL DEFAULT 0 COMMENT '是否禁用(0:未禁用 1 禁用)',
  `is_del` int(2) NOT NULL DEFAULT 0 COMMENT '是否删除(0:未删除 1:删除)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IDX_S_M_ID`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户表id',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码，MD5加密',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `question` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '找回密码问题',
  `answer` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '找回密码答案',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '最后一次更新时间',
  `last_login_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '上次登录时间',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `status` int(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户状态 0-正常 1-封禁',
  `is_del` int(2) NOT NULL DEFAULT 0 COMMENT '是否删除 0 未删除 1 已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `IDX_S_U_USERNAME`(`username`) USING BTREE,
  UNIQUE INDEX `IDX_S_U_ID`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户表(后台管理)' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
