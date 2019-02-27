package com.angel.provider.service;

import com.angel.base.constant.ServerResponse;
import com.angel.provider.model.vo.DataDictValueVo;

/**
 * feign 字典值
 */
public interface IDataDictValueFeignService {

    /**
     * 根据params查询
     * @param params params参数
     * @return 结果集包含单个实体
     */
    ServerResponse<DataDictValueVo> getDictValueByParams(String params);
}
