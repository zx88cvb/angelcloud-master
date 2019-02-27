package com.angel.provider.service;

import com.angel.base.service.ServiceResult;
import com.angel.provider.model.domain.DataDictValue;
import com.angel.provider.model.dto.DataDictValueDto;
import com.angel.provider.model.vo.DataDictValueVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 数据字典值Service
 * @Author: Angel
 * @Date: 2018/10/25.
 * @Description:
 */
public interface IDataDictValueService extends IService<DataDictValue> {

    /**
     * 获取数据字典值分页信息
     * @param dataDictValueDto 数据字典值dto
     * @return 返回ServiceResult 数据字典值结果集
     */
    ServiceResult<Page<DataDictValueVo>> getDataDictValuePage(DataDictValueDto dataDictValueDto);

    /**
     * 新增数据字典值
     * @param dataDictValueDto 数据字典值Dto
     * @return 返回ServiceResult int
     */
    ServiceResult<Integer> insertDataDictValue(DataDictValueDto dataDictValueDto);

    /**
     * 修改数据字典值
     * @param dataDictValueDto 数据字典值Dto
     * @return 返回ServiceResult int
     */
    ServiceResult<Integer> updateDataDictValue(DataDictValueDto dataDictValueDto);

    /**
     * 删除数据字典值
     * @param id id
     * @return ServiceResult int
     */
    ServiceResult<Integer> deleteDataDictValueById(int id);

    /**
     * 根据params参数获取实体
     * @param params 参数
     * @return 结果集包含单个实体
     */
    ServiceResult<DataDictValue> getEntityByParams(String params);

}
