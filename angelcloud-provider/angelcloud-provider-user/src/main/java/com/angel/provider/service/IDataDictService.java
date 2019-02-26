package com.angel.provider.service;

import com.angel.base.service.ServiceResult;
import com.angel.provider.model.domain.DataDict;
import com.angel.provider.model.domain.DataDictValue;
import com.angel.provider.model.dto.DataDictDto;
import com.angel.provider.model.vo.DataDictVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author: Angel
 * @Date: 2018/10/23.
 * @Description:
 */
public interface IDataDictService extends IService<DataDict> {

    /**
     * 获取数据字典分页信息
     * @param dataDictDto 数据字典dto
     * @return 返回ServiceResult 数据字典结果集
     */
    ServiceResult<Page<DataDictVo>> getDataDictPage(DataDictDto dataDictDto);

    /**
     * 新增数据字典
     * @param dataDictDto 数据字典Dto
     * @return 返回ServiceResult int
     */
    ServiceResult<Integer> insertDataDict(DataDictDto dataDictDto);

    /**
     * 修改数据字典
     * @param dataDictDto 数据字典Dto
     * @return 返回ServiceResult int
     */
    ServiceResult<Integer> updateDataDict(DataDictDto dataDictDto);

    /**
     * 删除数据字典
     * @param id id
     * @return ServiceResult int
     */
    ServiceResult<Integer> deleteDataDictById(int id);

    /**
     * 根据key查询字典值
     * @param key key
     * @return ServiceResult 字典Value
     */
    ServiceResult<List<DataDictValue>> getDictValueForKey(String key);

    /**
     * 根据key和params参数查询字典值
     * @param key
     * @param params
     * @return
     */
    ServiceResult<DataDictValue> getDictValueDetail(String key, String params);
}
