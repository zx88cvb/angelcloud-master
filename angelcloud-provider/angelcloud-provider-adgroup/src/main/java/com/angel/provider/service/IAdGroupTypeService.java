package com.angel.provider.service;

import com.angel.base.service.ServiceResult;
import com.angel.provider.model.domain.AdGroupType;
import com.angel.provider.model.dto.AdGroupTypeDto;
import com.angel.provider.model.vo.AdGroupTypeVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 广告组分类 服务
 * @Author: Angel
 * @Date: 2018/10/29.
 * @Description:
 */
public interface IAdGroupTypeService extends IService<AdGroupType> {
    /**
     * 分页条件查询广告组分类
     * @param adGroupTypeDto 条件实体类
     * @return 类别集合
     */
    ServiceResult<Page<AdGroupTypeVo>> getAdGroupTypePage (AdGroupTypeDto adGroupTypeDto);

    /**
     * 新增广告组分类
     * @param adGroupType 条件实体类
     * @return 返回新增个数
     */
    ServiceResult<Integer> insertAdGroupType (AdGroupType adGroupType);

    /**
     * 根据id查询广告组分类
     * @param id 广告组分类id
     * @return 广告组分类结果集
     */
    ServiceResult<AdGroupTypeDto> getAdGroupTypeById (Integer id);

    /**
     * 修改广告组分类
     * @param adGroupType 条件实体类
     * @return 返回修改个数
     */
    ServiceResult<Integer> updateAdGroupType (AdGroupType adGroupType);

    /**
     * 删除广告组分类
     * @param id 主键id
     * @return 返回删除个数结果集
     */
    ServiceResult<Integer> deleteAdGroupTypeById(int id);
}
