package com.angel.provider.service;

import com.angel.base.service.ServiceResult;
import com.angel.provider.model.domain.SysMenu;
import com.angel.provider.model.vo.SysMenuVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 后台菜单表 服务类
 * </p>
 *
 * @author aa
 * @since 2018-08-18
 */
public interface ISysMenuService extends IService<SysMenu> {

    /**'
     * 查询所有菜单
     * TODO 目前是所有用户都有权限 后期加权限
     * @return 返回SysMenuVo集合
     */
    ServiceResult<List<SysMenuVo>> getMenuList ();
}
