package com.angel.provider.service;

import com.angel.base.constant.ServerResponse;
import com.angel.provider.model.dto.UserInfo;
import com.angel.provider.model.vo.SysUserVo;
import com.angel.provider.service.hystrix.UserSysUserFeignHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户模块feign
 * author: Angel
 * Date: 2018/9/23.
 * Description: feign用户模块
 */
@FeignClient(name = "angelcloud-provider-user",fallback = UserSysUserFeignHystrix.class)
public interface UserSysUserFeignApi {

    /**
     * 根据用户id获取用户信息
     * @param userId 用户id
     * @return 返回ServiceResult<SysUserVo> 对象
     */
    @GetMapping("/api/user/getUserVo")
    ServerResponse<SysUserVo> getUserVo (@RequestParam(name = "userId") Integer userId);

    /**
     * 通过用户名查询用户、角色信息
     *
     * @param username 用户名
     * @return ServerResponse
     */
    @GetMapping("/user/info/{username}")
    ServerResponse<UserInfo> info(@PathVariable("username") String username);
}
