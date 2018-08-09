package com.angel.provider.web.frontend;


import com.angel.provider.model.domain.SysUser;
import com.angel.provider.service.ISysUserService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 系统用户表(后台管理) 前端控制器
 * </p>
 *
 * @author aa
 * @since 2018-07-30
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Resource
    private ISysUserService iSysUserService;

    @GetMapping(value = "selectall")
    public String selectAll () {
        Page<SysUser> page =new Page<SysUser>(0, 10);
        Page<SysUser> sysUserPage = iSysUserService.selectPage(page);
        List<SysUser> records = sysUserPage.getRecords();
        return records.toString();
    }

}

