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
     * @return 返回SysMenuVo集合
     */
    ServiceResult<List<SysMenuVo>> getMenuList();

    /**
     * 根据用户查询菜单
     * @param roles 角色id集合
     * @return 树形菜单
     */
    ServiceResult<List<SysMenuVo>> getMenuByRoleIds(List<Integer> roles);
}
