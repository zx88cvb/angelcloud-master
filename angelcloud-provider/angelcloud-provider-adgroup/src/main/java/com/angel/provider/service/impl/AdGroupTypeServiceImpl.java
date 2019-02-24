package com.angel.provider.service.impl;

import com.angel.base.constant.GlobalConstant;
import com.angel.base.service.ServiceResult;
import com.angel.provider.mapper.AdGroupTypeMapper;
import com.angel.provider.model.domain.AdGroupType;
import com.angel.provider.model.dto.AdGroupTypeDto;
import com.angel.provider.model.vo.AdGroupTypeVo;
import com.angel.provider.service.IAdGroupTypeService;
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
 * @Date: 2018/10/29.
 * @Description:
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class AdGroupTypeServiceImpl extends ServiceImpl<AdGroupTypeMapper, AdGroupType> implements IAdGroupTypeService {

    @Resource
    private AdGroupTypeMapper adGroupTypeMapper;

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ServiceResult<Page<AdGroupTypeVo>> getAdGroupTypePage(AdGroupTypeDto adGroupTypeDto) {
        Page<AdGroupTypeVo> page = new Page<AdGroupTypeVo>();
        //条件查询
        LambdaQueryWrapper<AdGroupType> entity = new QueryWrapper<AdGroupType>().lambda()
                .eq(AdGroupType:: getIsDel, GlobalConstant.IsDel.NO)
                .orderByDesc(AdGroupType:: getCreateTime);

        //判断名称为不为空
        if (StringUtils.isNotBlank(adGroupTypeDto.getName())) {
            entity.like(AdGroupType:: getName, adGroupTypeDto.getName());
        }

        // 判断表示不为空
        if (StringUtils.isNotBlank(adGroupTypeDto.getTypeKey())) {
            entity.eq(AdGroupType:: getTypeKey, adGroupTypeDto.getTypeKey());
        }

        IPage<AdGroupType> iPageAdGroupType = adGroupTypeMapper.selectPage(new Page<>(adGroupTypeDto.getPageNum(), adGroupTypeDto.getPageSize()), entity);

        // 获取集合对象
        List<AdGroupType> adGroupTypeList = iPageAdGroupType.getRecords();

        //将AdGroupType 转换成Vo对象
        List<AdGroupTypeVo> collect = adGroupTypeList.stream().map(e -> {
            AdGroupTypeVo adGroupTypeVo = new AdGroupTypeVo();
            BeanUtils.copyProperties(e, adGroupTypeVo);
            return adGroupTypeVo;
        }).collect(Collectors.toList());

        BeanUtils.copyProperties(iPageAdGroupType, page);
        page.setRecords(collect);


        return ServiceResult.of(page);
    }

    @Override
    public ServiceResult<Integer> insertAdGroupType(AdGroupType adGroupType) {
        //返回个数
        Integer count = adGroupTypeMapper.insert(adGroupType);
        if (count < 1) {
            return ServiceResult.notFound();
        }
        return ServiceResult.of(count);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ServiceResult<AdGroupTypeDto> getAdGroupTypeById(Integer id) {
        AdGroupType adGroupType = adGroupTypeMapper.selectById(id);

        if (adGroupType == null) {
            return ServiceResult.notFound();
        }

        AdGroupTypeDto AdGroupTypeDto = new AdGroupTypeDto();
        BeanUtils.copyProperties(adGroupType, AdGroupTypeDto);

        return ServiceResult.of(AdGroupTypeDto);
    }

    @Override
    public ServiceResult<Integer> updateAdGroupType(AdGroupType adGroupType) {
        adGroupType.setUpdateTime(new Date());
        //返回个数
        Integer count = adGroupTypeMapper.updateById(adGroupType);
        if (count < 1) {
            return ServiceResult.notFound();
        }
        return ServiceResult.of(count);
    }

    @Override
    public ServiceResult<Integer> deleteAdGroupTypeById(int id) {
        AdGroupType adGroupType = new AdGroupType();
        adGroupType.setId(id);
        adGroupType.setIsDel(GlobalConstant.IsDel.YES);
        adGroupType.setUpdateTime(new Date());
        Integer count = adGroupTypeMapper.updateById(adGroupType);
        if (count < 1) {
            return ServiceResult.notFound();
        }
        return ServiceResult.of(count);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ServiceResult<List<AdGroupType>> getAdGroupTypeList(AdGroupTypeDto adGroupTypeDto) {
        //条件查询
        LambdaQueryWrapper<AdGroupType> entity = new QueryWrapper<AdGroupType>().lambda()
                .eq(AdGroupType:: getIsDel, GlobalConstant.IsDel.NO)
                .orderByDesc(AdGroupType:: getCreateTime);

        //判断名称为不为空
        if (StringUtils.isNotBlank(adGroupTypeDto.getName())) {
            entity.like(AdGroupType:: getName, adGroupTypeDto.getName());
        }

        // 判断表示不为空
        if (StringUtils.isNotBlank(adGroupTypeDto.getTypeKey())) {
            entity.eq(AdGroupType:: getTypeKey, adGroupTypeDto.getTypeKey());
        }

        List<AdGroupType> adGroupTypeList = adGroupTypeMapper.selectList(entity);


        return ServiceResult.of(adGroupTypeList);
    }
}
