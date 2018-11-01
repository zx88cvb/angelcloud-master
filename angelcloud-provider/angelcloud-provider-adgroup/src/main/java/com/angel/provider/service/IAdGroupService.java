package com.angel.provider.service;

import com.angel.base.service.ServiceResult;
import com.angel.provider.model.domain.AdGroup;
import com.angel.provider.model.domain.AdGroupType;
import com.angel.provider.model.dto.AdGroupDto;
import com.angel.provider.model.vo.AdGroupVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 广告组 服务层
 * @Author: Angel
 * @Date: 2018/11/1.
 * @Description:
 */
public interface IAdGroupService extends IService<AdGroup> {
    /**
     * 分页条件查询广告组
     * @param adGroupDto 条件实体类
     * @return 类别集合
     */
    ServiceResult<Page<AdGroupVo>> getAdGroupPage (AdGroupDto adGroupDto);

    /**
     * 新增广告组
     * @param adGroupDto 条件实体类DTO
     * @return 返回新增个数
     */
    ServiceResult<Integer> insertAdGroup (AdGroupDto adGroupDto);

    /**
     * 根据id查询广告组
     * @param id 广告组id
     * @return 广告组结果集
     */
    ServiceResult<AdGroupDto> getAdGroupById (Integer id);

    /**
     * 修改广告组
     * @param adGroupDto 条件实体类DTO
     * @return 返回修改个数
     */
    ServiceResult<Integer> updateAdGroup (AdGroupDto adGroupDto);

    /**
     * 删除广告组
     * @param id 主键id
     * @return 返回删除个数结果集
     */
    ServiceResult<Integer> deleteAdGroupById(int id);
}
