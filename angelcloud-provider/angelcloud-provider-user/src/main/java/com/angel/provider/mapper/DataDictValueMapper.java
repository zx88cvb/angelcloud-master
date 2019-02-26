package com.angel.provider.mapper;


import com.angel.provider.model.domain.DataDictValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataDictValueMapper extends BaseMapper<DataDictValue>{
    /**
     * 根据key查询字典值
     * @param key key
     * @return DataDictValue集合
     */
    List<DataDictValue> getDictValueForKey(String key);

    /**
     * 根据key和参数params查询字典值详情
     * @param key key
     * @param params 参数
     * @return 单个实体
     */
    DataDictValue getDictValueDetail(@Param("key") String key, @Param("params") String params);
}