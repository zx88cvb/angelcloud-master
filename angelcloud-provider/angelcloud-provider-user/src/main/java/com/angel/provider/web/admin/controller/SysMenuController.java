package com.angel.provider.web.admin.controller;


import com.angel.base.constant.ServerResponse;
import com.angel.base.service.ServiceResult;
import com.angel.provider.model.vo.SysMenuVo;
import com.angel.provider.service.ISysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 后台菜单表 前端控制器
 * </p>
 *
 * @author aa
 * @since 2018-08-18
 */
@RestController
@RequestMapping("/sysMenu")
@Api("后台菜单API")
public class SysMenuController {

    @Resource
    private ISysMenuService iSysMenuService;

    /**
     * 查询全部菜单列表
     * @param request request
     * @return 返回菜单列表集合
     */
    @GetMapping("getmenulist")
    @ApiOperation(value = "获取菜单列表", httpMethod = "GET")
    public ServerResponse<List<SysMenuVo>> getMenuList (HttpServletRequest request) {
        ServiceResult<List<SysMenuVo>> menuList = iSysMenuService.getMenuList();
        if (!menuList.isSuccess()) {
            return ServerResponse.createByError();
        }

        return ServerResponse.createBySuccess(menuList.getResult());
    }
}

