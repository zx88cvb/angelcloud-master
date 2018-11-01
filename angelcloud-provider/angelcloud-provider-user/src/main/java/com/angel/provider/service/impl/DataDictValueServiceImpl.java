package com.angel.provider.service.impl;

import com.angel.base.constant.GlobalConstant;
import com.angel.base.service.ServiceResult;
import com.angel.provider.mapper.DataDictValueMapper;
import com.angel.provider.model.domain.DataDictValue;
import com.angel.provider.model.dto.DataDictValueDto;
import com.angel.provider.model.vo.DataDictValueVo;
import com.angel.provider.service.IDataDictValueService;
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
 * @Date: 2018/10/25.
 * @Description:
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class DataDictValueServiceImpl extends ServiceImpl<DataDictValueMapper, DataDictValue> implements IDataDictValueService {

    @Resource
    private DataDictValueMapper dataDictValueMapper;


    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ServiceResult<Page<DataDictValueVo>> getDataDictValuePage(DataDictValueDto dataDictValueDto) {
        DataDictValue dataDictValue = new DataDictValue();
        BeanUtils.copyProperties(dataDictValueDto, dataDictValue);
        //条件查询
        LambdaQueryWrapper<DataDictValue> entity = new QueryWrapper<DataDictValue>().lambda()
                .eq(DataDictValue:: getIsDel, GlobalConstant.IsDel.NO)
                .orderByDesc(DataDictValue:: getCreateTime);

        //判断dataDictId为不为空
        if (dataDictValue.getDataDictId() != null) {
            entity.like(DataDictValue:: getDataDictId, dataDictValue.getDataDictId());
        }

        // 判断显示名称不为空
        if (StringUtils.isNotBlank(dataDictValue.getShowValue())) {
            entity.like(DataDictValue:: getShowValue, dataDictValue.getShowValue());
        }

        IPage<DataDictValue> iPageDataDictValue = dataDictValueMapper.selectPage(new Page<>(dataDictValue.getPageNum(), dataDictValue.getPageSize()), entity);

        // 获取集合对象
        List<DataDictValue> dataDictValueList = iPageDataDictValue.getRecords();

        //将DataDict 转换成Vo对象
        List<DataDictValueVo> collect = dataDictValueList.stream().map(e -> {
            DataDictValueVo dataDictValueVo = new DataDictValueVo();
            BeanUtils.copyProperties(e, dataDictValueVo);
            return dataDictValueVo;
        }).collect(Collectors.toList());

        //将DataDict 转换成Vo对象
        Page<DataDictValueVo> page = new Page<>();
        BeanUtils.copyProperties(iPageDataDictValue, page);
        page.setRecords(collect);


        return ServiceResult.of(page);
    }

    @Override
    public ServiceResult<Integer> insertDataDictValue(DataDictValueDto dataDictValueDto) {
        if (dataDictValueDto == null) {
            return ServiceResult.notFound();
        }
        DataDictValue dataDictValue = new DataDictValue();
        BeanUtils.copyProperties(dataDictValueDto, dataDictValue);

        //返回个数
        int result = dataDictValueMapper.insert(dataDictValue);

        if (result < GlobalConstant.Attribute.YES) {
            return ServiceResult.notFound();
        }
        return ServiceResult.of(result);
    }

    @Override
    public ServiceResult<Integer> updateDataDictValue(DataDictValueDto dataDictValueDto) {
        if (dataDictValueDto == null) {
            return ServiceResult.notFound();
        }
        DataDictValue dataDictValue = new DataDictValue();
        BeanUtils.copyProperties(dataDictValueDto, dataDictValue);
        dataDictValue.setUpdateTime(new Date());

        //返回个数
        int result = dataDictValueMapper.updateById(dataDictValue);

        if (result < GlobalConstant.Attribute.YES) {
            return ServiceResult.notFound();
        }
        return ServiceResult.of(result);
    }

    @Override
    public ServiceResult<Integer> deleteDataDictValueById(int id) {
        DataDictValue dataDictValue = new DataDictValue();
        dataDictValue.setId(id);
        dataDictValue.setIsDel(GlobalConstant.IsDel.YES);
        dataDictValue.setUpdateTime(new Date());
        Integer count = dataDictValueMapper.updateById(dataDictValue);
        if (count < GlobalConstant.Attribute.YES) {
            return ServiceResult.notFound();
        }
        return ServiceResult.of(count);
    }
}
