package com.angel.provider.service;

import com.angel.base.service.ServiceResult;
import com.angel.provider.model.domain.AdGroupContent;
import com.angel.provider.model.dto.AdGroupContentDto;
import com.angel.provider.model.vo.AdGroupContentVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 广告内容 服务层
 * @Author: Angel
 * @Date: 2018/11/4.
 * @Description:
 */
public interface IAdGroupContentService extends IService<AdGroupContent> {
    /**
     * 分页条件查询广告内容
     * @param adGroupContentDto 条件实体类
     * @return 类别集合
     */
    ServiceResult<Page<AdGroupContentVo>> getAdGroupContentPage (AdGroupContentDto adGroupContentDto);

    /**
     * 新增广告内容
     * @param adGroupContentDto 条件实体类DTO
     * @return 返回新增个数
     */
    ServiceResult<Integer> insertAdGroupContent (AdGroupContentDto adGroupContentDto);

    /**
     * 根据id查询广告内容
     * @param id 广告组id
     * @return 广告组结果集
     */
    ServiceResult<AdGroupContentDto> getAdGroupContentById (Integer id);

    /**
     * 修改广告内容
     * @param adGroupContentDto 条件实体类DTO
     * @return 返回修改个数
     */
    ServiceResult<Integer> updateAdGroupContent (AdGroupContentDto adGroupContentDto);

    /**
     * 删除广告内容
     * @param id 主键id
     * @return 返回删除个数结果集
     */
    ServiceResult<Integer> deleteAdGroupContentById(int id);

    /**
     * 根据id查询
     * @param id 主键id
     * @return 单个实体
     */
    ServiceResult<AdGroupContent> selectById(Integer id);

    /**
     * 根据类型key和广告组key 查询广告内容
     * @param typeKey 类型key
     * @param adKey 广告组key
     * @return 集合Vo
     */
    ServiceResult<List<AdGroupContentVo>> getContentByTypeAndGroup(String typeKey, String adKey);
}
