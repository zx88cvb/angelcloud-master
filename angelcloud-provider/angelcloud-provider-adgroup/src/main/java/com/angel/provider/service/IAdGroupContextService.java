package com.angel.provider.service;

import com.angel.base.service.ServiceResult;
import com.angel.provider.model.domain.AdGroupContext;
import com.angel.provider.model.dto.AdGroupContextDto;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 广告内容详情Service
 * @Author: Angel
 * @Date: 2019/03/04.
 * @Description:
 */
public interface IAdGroupContextService extends IService<AdGroupContext> {

    /**
     * 分页条件查询广告内容详情
     * @param adGroupContextDto 条件实体类
     * @return 类别集合
     */
    ServiceResult<Page<AdGroupContext>> getConditionPage (AdGroupContextDto adGroupContextDto);

    /**
     * 新增广告内容详情
     * @param adGroupContextDto 条件实体类DTO
     * @return 返回新增个数
     */
    ServiceResult<Integer> insert (AdGroupContextDto adGroupContextDto);

    /**
     * 根据id查询广告内容详情
     * @param id 广告组id
     * @return 广告组结果集
     */
    ServiceResult<AdGroupContext> selectById (Integer id);

    /**
     * 修改广告内容详情
     * @param adGroupContextDto 条件实体类DTO
     * @return 返回修改个数
     */
    ServiceResult<Integer> update (AdGroupContextDto adGroupContextDto);

    /**
     * 删除广告内容详情
     * @param id 主键id
     * @return 返回删除个数结果集
     */
    ServiceResult<Integer> deleteById(int id);
}
