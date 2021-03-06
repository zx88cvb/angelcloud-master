package com.angel.provider.service.impl;

import com.angel.base.constant.GlobalConstant;
import com.angel.base.service.ServiceResult;
import com.angel.provider.mapper.SysMenuMapper;
import com.angel.provider.model.constant.MenuConstant;
import com.angel.provider.model.domain.SysMenu;
import com.angel.provider.model.vo.SysMenuVo;
import com.angel.provider.service.ISysMenuService;
import com.angel.provider.utils.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 后台菜单表 服务实现类
 * </p>
 *
 * @author aa
 * @since 2018-08-18
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Resource
    private SysMenuMapper sysMenuMapper;


    /**'
     * 查询所有菜单
     * TODO 目前是所有用户都有权限 后期加权限
     * @return 返回SysMenuVo集合
     */
    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ServiceResult<List<SysMenuVo>> getMenuList() {
        LambdaQueryWrapper<SysMenu> sysMenuWrapper = new QueryWrapper<SysMenu>()
                .lambda()
                .eq(SysMenu:: getIsActivity, MenuConstant.isActivity.NO)
                .eq(SysMenu:: getIsDel, GlobalConstant.IsDel.NO);
        List<SysMenu> sysMenuList = sysMenuMapper.selectList(sysMenuWrapper);
        List<SysMenuVo> sysMenuVoList = sysMenuList.stream().map(e -> {
            SysMenuVo sysMenuVo = new SysMenuVo();
            BeanUtils.copyProperties(e, sysMenuVo);
            return sysMenuVo;
        }).collect(Collectors.toList());
        List<SysMenuVo> menuVoList = TreeUtil.getChildMenuVoList(sysMenuVoList, MenuConstant.MENU_LEVEL_ROOT);
        return ServiceResult.of(menuVoList);
    }
}
