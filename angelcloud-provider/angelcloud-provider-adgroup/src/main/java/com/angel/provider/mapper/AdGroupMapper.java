package com.angel.provider.mapper;

import com.angel.provider.model.domain.AdGroup;
import com.angel.provider.model.dto.AdGroupDto;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * 广告组Mapper
 */
public interface AdGroupMapper extends BaseMapper<AdGroup> {

    /**
     * 分页条件查询
     * @param page page
     * @param adGroupDto dto
     * @return Ipage对象
     */
    IPage<AdGroup> selectPageByCondition(Page page, @Param("adGroupDto") AdGroupDto adGroupDto);
}