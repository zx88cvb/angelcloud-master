package com.angel.provider.mapper;


import com.angel.provider.model.domain.DataDictValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface DataDictValueMapper extends BaseMapper<DataDictValue>{
    /**
     * 根据key查询字典值
     * @param key key
     * @return DataDictValue集合
     */
    List<DataDictValue> getDictValueForKey(String key);
}