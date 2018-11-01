package com.angel.provider.service.impl;

import com.angel.base.constant.GlobalConstant;
import com.angel.base.service.ServiceResult;
import com.angel.provider.mapper.AdGroupMapper;
import com.angel.provider.model.domain.AdGroup;
import com.angel.provider.model.domain.AdGroupType;
import com.angel.provider.model.dto.AdGroupDto;
import com.angel.provider.model.vo.AdGroupVo;
import com.angel.provider.service.IAdGroupService;
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
 * @Date: 2018/11/1.
 * @Description:
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class AdGroupServiceImpl extends ServiceImpl<AdGroupMapper, AdGroup> implements IAdGroupService {

    @Resource
    private AdGroupMapper adGroupMapper;

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ServiceResult<Page<AdGroupVo>> getAdGroupPage(AdGroupDto adGroupDto) {
        Page<AdGroupVo> page = new Page<AdGroupVo>();
        //条件查询
        LambdaQueryWrapper<AdGroup> entity = new QueryWrapper<AdGroup>().lambda()
                .eq(AdGroup:: getIsDel, GlobalConstant.IsDel.NO)
                .orderByDesc(AdGroup:: getCreateTime);

        //判断分类ID为不为空
        if (adGroupDto.getTypeId() != null) {
            entity.eq(AdGroup:: getTypeId, adGroupDto.getTypeId());
        }

        //判断名称为不为空
        if (StringUtils.isNotBlank(adGroupDto.getName())) {
            entity.like(AdGroup:: getName, adGroupDto.getName());
        }

        // 判断标识不为空
        if (StringUtils.isNotBlank(adGroupDto.getAdKey())) {
            entity.eq(AdGroup:: getAdKey, adGroupDto.getAdKey());
        }

        IPage<AdGroup> iPageAdGroup = adGroupMapper.selectPage(new Page<>(adGroupDto.getPageNum(), adGroupDto.getPageSize()), entity);

        // 获取集合对象
        List<AdGroup> adGroupList = iPageAdGroup.getRecords();

        //将AdGroup 转换成Vo对象
        List<AdGroupVo> collect = adGroupList.stream().map(e -> {
            AdGroupVo adGroupVo = new AdGroupVo();
            BeanUtils.copyProperties(e, adGroupVo);
            return adGroupVo;
        }).collect(Collectors.toList());

        BeanUtils.copyProperties(iPageAdGroup, page);
        page.setRecords(collect);


        return ServiceResult.of(page);
    }

    @Override
    public ServiceResult<Integer> insertAdGroup(AdGroupDto adGroupDto) {
        AdGroup adGroup = new AdGroup();
        BeanUtils.copyProperties(adGroupDto, adGroup);
        //返回个数
        Integer count = adGroupMapper.insert(adGroup);
        if (count < 1) {
            return ServiceResult.notFound();
        }
        return ServiceResult.of(count);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ServiceResult<AdGroupDto> getAdGroupById(Integer id) {
        AdGroup adGroup = adGroupMapper.selectById(id);

        if (adGroup == null) {
            return ServiceResult.notFound();
        }

        AdGroupDto AdGroupDto = new AdGroupDto();
        BeanUtils.copyProperties(adGroup, AdGroupDto);

        return ServiceResult.of(AdGroupDto);
    }

    @Override
    public ServiceResult<Integer> updateAdGroup(AdGroupDto adGroupDto) {
        AdGroup adGroup = new AdGroup();
        BeanUtils.copyProperties(adGroupDto, adGroup);
        adGroup.setUpdateTime(new Date());
        //返回个数
        Integer count = adGroupMapper.updateById(adGroup);
        if (count < 1) {
            return ServiceResult.notFound();
        }
        return ServiceResult.of(count);
    }

    @Override
    public ServiceResult<Integer> deleteAdGroupById(int id) {
        AdGroup adGroup = new AdGroup();
        adGroup.setId(id);
        adGroup.setIsDel(GlobalConstant.IsDel.YES);
        adGroup.setUpdateTime(new Date());
        Integer count = adGroupMapper.updateById(adGroup);
        if (count < 1) {
            return ServiceResult.notFound();
        }
        return ServiceResult.of(count);
    }
}
