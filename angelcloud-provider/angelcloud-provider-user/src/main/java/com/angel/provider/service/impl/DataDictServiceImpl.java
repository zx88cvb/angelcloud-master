package com.angel.provider.service.impl;

import com.angel.base.constant.GlobalConstant;
import com.angel.base.service.ServiceResult;
import com.angel.provider.mapper.DataDictMapper;
import com.angel.provider.mapper.DataDictValueMapper;
import com.angel.provider.model.domain.DataDict;
import com.angel.provider.model.domain.DataDictValue;
import com.angel.provider.model.dto.DataDictDto;
import com.angel.provider.model.vo.DataDictVo;
import com.angel.provider.service.IDataDictService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Angel
 * @Date: 2018/10/23.
 * @Description:
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class DataDictServiceImpl extends ServiceImpl<DataDictMapper, DataDict> implements IDataDictService {

    @Resource
    private DataDictMapper dataDictMapper;

    @Resource
    private DataDictValueMapper dataDictValueMapper;

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ServiceResult<Page<DataDictVo>> getDataDictPage(DataDictDto dataDictDto) {
        DataDict dataDict = new DataDict();
        BeanUtils.copyProperties(dataDictDto, dataDict);
        //条件查询
        LambdaQueryWrapper<DataDict> entity = new QueryWrapper<DataDict>().lambda()
                .eq(DataDict:: getIsDel, GlobalConstant.IsDel.NO)
                .orderByDesc(DataDict:: getCreateTime);

        //判断dictKey为不为空
        if (StringUtils.isNotBlank(dataDict.getDictKey())) {
            entity.like(DataDict:: getDictKey, dataDict.getDictKey());
        }

        IPage<DataDict> iPageDataDict = dataDictMapper.selectPage(new Page<>(dataDict.getPageNum(), dataDict.getPageSize()), entity);

        // 获取集合对象
        List<DataDict> dataDictList = iPageDataDict.getRecords();

        //将DataDict 转换成Vo对象
        List<DataDictVo> collect = dataDictList.stream().map(e -> {
            DataDictVo dataDictVo = new DataDictVo();
            BeanUtils.copyProperties(e, dataDictVo);
            return dataDictVo;
        }).collect(Collectors.toList());

        //将DataDict 转换成Vo对象
        Page<DataDictVo> page = new Page<DataDictVo>();
        BeanUtils.copyProperties(iPageDataDict, page);
        page.setRecords(collect);


        return ServiceResult.of(page);
    }

    @Override
    public ServiceResult<Integer> insertDataDict(DataDictDto dataDictDto) {
        if (dataDictDto == null) {
            return ServiceResult.notFound();
        }
        DataDict dataDict = new DataDict();
        BeanUtils.copyProperties(dataDictDto, dataDict);

        //返回个数
        int result = dataDictMapper.insert(dataDict);

        if (result < GlobalConstant.Attribute.YES) {
            return ServiceResult.notFound();
        }
        return ServiceResult.of(result);
    }

    @Override
    public ServiceResult<Integer> updateDataDict(DataDictDto dataDictDto) {
        if (dataDictDto == null) {
            return ServiceResult.notFound();
        }
        DataDict dataDict = new DataDict();
        BeanUtils.copyProperties(dataDictDto, dataDict);
        dataDict.setUpdateTime(new Date());

        //返回个数
        int result = dataDictMapper.updateById(dataDict);

        if (result < GlobalConstant.Attribute.YES) {
            return ServiceResult.notFound();
        }
        return ServiceResult.of(result);
    }

    @Override
    public ServiceResult<Integer> deleteDataDictById(int id) {
        DataDict dataDict = new DataDict();
        dataDict.setId(id);
        dataDict.setIsDel(GlobalConstant.IsDel.YES);
        dataDict.setUpdateTime(new Date());
        Integer count = dataDictMapper.updateById(dataDict);
        if (count < GlobalConstant.Attribute.YES) {
            return ServiceResult.notFound();
        }
        return ServiceResult.of(count);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ServiceResult<List<DataDictValue>> getDictValueForKey(String key) {
        List<DataDictValue> dataDictValueList = dataDictValueMapper.getDictValueForKey(key);
        if (dataDictValueList == null) {
            return ServiceResult.notFound();
        }
        return ServiceResult.of(dataDictValueList);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ServiceResult<DataDictValue> getDictValueDetail(String key, String params) {
        DataDictValue dataDictValue = dataDictValueMapper.getDictValueDetail(key, params);
        if (dataDictValue == null) {
            return ServiceResult.notFound();
        }
        return ServiceResult.of(dataDictValue);
    }
}
