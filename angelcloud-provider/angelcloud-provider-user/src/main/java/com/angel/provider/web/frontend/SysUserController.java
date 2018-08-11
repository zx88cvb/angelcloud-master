package com.angel.provider.web.frontend;


import com.angel.base.constant.ResponseCode;
import com.angel.base.constant.ServerResponse;
import com.angel.base.enums.ErrorCodeEnum;
import com.angel.base.service.ServiceResult;
import com.angel.provider.model.domain.SysUser;
import com.angel.provider.model.form.SysUserForm;
import com.angel.provider.service.ISysUserService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    /**
     * 测试
     * @return 返回用户json字符串
     */
    @GetMapping(value = "selectall")
    public String selectAll () {
        Page<SysUser> page =new Page<SysUser>(0, 10);
        Page<SysUser> sysUserPage = iSysUserService.selectPage(page);
        List<SysUser> records = sysUserPage.getRecords();
        return records.toString();
    }

    /**
     * 登录
     * @param request request
     * @param username 用户名
     * @param password 密码
     * @return 返回结果集
     */
    @GetMapping(value = "login")
    public ServerResponse login (HttpServletRequest request,
                                 String username, String password) {
        ServiceResult result = iSysUserService.login(username, password);
        if (!result.isSuccess()) {
            return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.UAC10011016.code(),ErrorCodeEnum.UAC10011016.msg());
        }

        return ServerResponse.createBySuccess();
    }

}

