package com.angel.provider.service;

import com.angel.base.service.ServiceResult;
import com.angel.provider.model.domain.AdGroupItem;
import com.angel.provider.model.dto.AdGroupItemDto;
import com.angel.provider.model.vo.AdGroupItemVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 广告项服务层
 * @Author: Angel
 * @Date: 2018/11/4.
 * @Description:
 */
public interface IAdGroupItemService extends IService<AdGroupItem> {
    /**
     * 分页条件查询广告项
     * @param adGroupItemDto 条件实体类
     * @return 类别集合
     */
    ServiceResult<Page<AdGroupItemVo>> getAdGroupItemPage (AdGroupItemDto adGroupItemDto);

    /**
     * 新增广告项
     * @param adGroupItemDto 条件实体类DTO
     * @return 返回新增个数
     */
    ServiceResult<Integer> insertAdGroupItem (AdGroupItemDto adGroupItemDto);

    /**
     * 根据id查询广告组
     * @param id 广告组id
     * @return 广告组结果集
     */
    ServiceResult<AdGroupItemDto> getAdGroupItemById (Integer id);

    /**
     * 修改广告组
     * @param adGroupItemDto 条件实体类DTO
     * @return 返回修改个数
     */
    ServiceResult<Integer> updateAdGroupItem (AdGroupItemDto adGroupItemDto);

    /**
     * 删除广告组
     * @param id 主键id
     * @return 返回删除个数结果集
     */
    ServiceResult<Integer> deleteAdGroupItemById(int id);
}
