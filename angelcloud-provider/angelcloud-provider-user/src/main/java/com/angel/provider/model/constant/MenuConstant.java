/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：MenuConstant.java
 * 创建人：刘兆明
 * 联系方式：paascloud.net@gmail.com
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */

package com.angel.provider.model.constant;

/**
 * The class Menu constant.
 *
 * @author Angel
 */
public class MenuConstant {
	/**
	 * 菜单根目录
	 */
	public static final Integer MENU_LEVEL_ROOT = 0;
	/**
	 * 一级菜单
	 */
	public static final Integer MENU_LEVEL_FIRST = 1;
	/**
	 * 二级菜单
	 */
	public static final Integer MENU_LEVEL_TWO = 2;
	/**
	 * 三级菜单
	 */
	public static final Integer MENU_LEVEL_THREE = 3;

	/**
	 * 是否禁用(0:未禁用 1 禁用)
	 */
	public interface isActivity {
		/**
		 * 未禁用
		 */
		Integer NO = 0;

		/**
		 * 禁用
		 */
		Integer YES = 1;
	}

}
