package com.angel.provider.mapper;

import com.angel.provider.model.domain.SysMenu;
import com.angel.provider.model.vo.SysMenuVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 后台菜单表 Mapper 接口
 * </p>
 *
 * @author angel
 * @since 2018-08-18
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 通过角色编号查询菜单
     *
     * @param roleId 角色ID
     * @return
     */
    List<SysMenuVo> findMenuByRoleId(Integer roleId);

    /**
     * 根据角色集合查询菜单
     * @param list 角色id集合
     * @return 菜单集合
     */
    List<SysMenuVo> findMenuByRoleIds(List<Integer> list);
}
