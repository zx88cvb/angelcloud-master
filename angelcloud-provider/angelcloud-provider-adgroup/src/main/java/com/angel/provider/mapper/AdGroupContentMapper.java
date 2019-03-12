package com.angel.provider.mapper;

import com.angel.provider.model.domain.AdGroupContent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 广告内容Mapper
 */
public interface AdGroupContentMapper extends BaseMapper<AdGroupContent> {
    /**
     * 根据id查询全部信息
     * @param id id主键
     * @return 单个实体
     */
    AdGroupContent selectByAllId(Integer id);
}