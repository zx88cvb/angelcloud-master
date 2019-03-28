package com.angel.provider.mapper;

import com.angel.provider.model.domain.AdGroupContent;
import com.angel.provider.model.dto.AdGroupContentDto;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 条件查询
     * @param page page对象 可忽略
     * @param adGroupContentDto 广告内容DTO实体
     * @return IPage
     */
    IPage<AdGroupContent> selectByCondition(Page page,
                                            @Param("adGroupContentDto") AdGroupContentDto adGroupContentDto);

    /**
     * 根据类型key和广告组key 查询广告内容
     * @param typeKey 类型key
     * @param adKey 广告组key
     * @return 集合content
     */
    List<AdGroupContent> getContentByTypeAndGroup(@Param("typeKey") String typeKey,
                                                  @Param("adKey") String adKey);
}