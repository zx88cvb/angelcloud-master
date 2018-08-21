package com.angel.provider.utils;

import com.angel.provider.model.vo.SysMenuVo;
import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 生成树菜单
 * @Author: Angel
 * @Date: 2018/8/21.
 * @Description: 生成树菜单
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TreeUtil {

    /**
     * 根据父节点的ID获取所有子节点
     * @param list 菜单集合
     * @param parentId 父节点id
     * @return 数结构集合
     */
    public static List<SysMenuVo> getChildMenuVoList (List<SysMenuVo> list, Integer parentId) {
        List<SysMenuVo> returnList = Lists.newArrayList();

        for(SysMenuVo sysMenuVo : list) {
            if (sysMenuVo.getPId() == null) {
                continue;
            }

            if (sysMenuVo.getPId() == parentId) {
                recursionMenuVo(list, sysMenuVo);
                returnList.add(sysMenuVo);
            }
        }
        return returnList;
    }

    /**
     * 递归调用
     * @param list 菜单集合
     * @param entity 当前菜单
     */
    public static void recursionMenuVo (List<SysMenuVo> list, SysMenuVo entity) {
        List<SysMenuVo> childList = getChildList(list, entity);
        entity.setSubMenuList(childList);

        for (SysMenuVo sysMenuVo : childList) {
            //判断是否有子节点
            if (hasChild(list, sysMenuVo)) {
                for (SysMenuVo childMenu : childList) {
                    recursionMenuVo(list, childMenu);
                }
            }
        }
    }

    /**
     * 获取子节点列表
     * @param list
     * @param entity
     * @return 返回子节点集合
     */
    public static List<SysMenuVo> getChildList (List<SysMenuVo> list, SysMenuVo entity) {
        List<SysMenuVo> returnList = Lists.newArrayList();

        for(SysMenuVo sysMenuVo : list) {
            if (sysMenuVo.getPId() == null) {
                continue;
            }

            if (sysMenuVo.getPId() == entity.getId()) {
                returnList.add(sysMenuVo);
            }
        }
        return returnList;
    }

    /**
     * 判断是否有子节点
     */
    private static boolean hasChild(List<SysMenuVo> list, SysMenuVo entity) {
        return !getChildList(list, entity).isEmpty();
    }
}
