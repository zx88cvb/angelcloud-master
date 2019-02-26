package com.angel.provider.service.impl;

import com.angel.base.constant.GlobalConstant;
import com.angel.base.service.ServiceResult;
import com.angel.provider.mapper.AdGroupItemMapper;
import com.angel.provider.model.domain.AdGroupItem;
import com.angel.provider.model.dto.AdGroupItemDto;
import com.angel.provider.model.vo.AdGroupItemVo;
import com.angel.provider.service.IAdGroupItemService;
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
 * @Date: 2018/11/4.
 * @Description:
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class AdGroupItemServiceImpl extends ServiceImpl<AdGroupItemMapper, AdGroupItem> implements IAdGroupItemService {

    @Resource
    private AdGroupItemMapper adGroupItemMapper;

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ServiceResult<Page<AdGroupItemVo>> getAdGroupItemPage(AdGroupItemDto adGroupItemDto) {
        Page<AdGroupItemVo> page = new Page<AdGroupItemVo>();
        //条件查询
        LambdaQueryWrapper<AdGroupItem> entity = new QueryWrapper<AdGroupItem>().lambda()
                .eq(AdGroupItem:: getIsDel, GlobalConstant.IsDel.NO)
                .orderByDesc(AdGroupItem:: getCreateTime);

        //判断广告组外键为不为空
        if (adGroupItemDto.getGroupId() != null) {
            entity.eq(AdGroupItem:: getGroupId, adGroupItemDto.getGroupId());
        }

        //判断类型为不为空
        if (adGroupItemDto.getType() != null) {
            entity.eq(AdGroupItem:: getType, adGroupItemDto.getType());
        }

        //判断名称为不为空
        if (StringUtils.isNotBlank(adGroupItemDto.getName())) {
            entity.like(AdGroupItem:: getName, adGroupItemDto.getName());
        }

        // 判断标识不为空
        if (StringUtils.isNotBlank(adGroupItemDto.getFlag())) {
            entity.eq(AdGroupItem:: getFlag, adGroupItemDto.getFlag());
        }

        IPage<AdGroupItem> iPageAdGroupItem = adGroupItemMapper.
                selectPage(new Page<>(adGroupItemDto.getPageNum(), adGroupItemDto.getPageSize()), entity);

        // 获取集合对象
        List<AdGroupItem> adGroupItemList = iPageAdGroupItem.getRecords();

        //将AdGroup 转换成Vo对象
        List<AdGroupItemVo> collect = adGroupItemList.stream().map(e -> {
            AdGroupItemVo adGroupItemVo = new AdGroupItemVo();
            BeanUtils.copyProperties(e, adGroupItemVo);
            return adGroupItemVo;
        }).collect(Collectors.toList());

        BeanUtils.copyProperties(iPageAdGroupItem, page);
        page.setRecords(collect);


        return ServiceResult.of(page);
    }

    @Override
    public ServiceResult<Integer> insertAdGroupItem(AdGroupItemDto adGroupItemDto) {
        AdGroupItem adGroupItem = new AdGroupItem();
        BeanUtils.copyProperties(adGroupItemDto, adGroupItem);
        //返回个数
        Integer count = adGroupItemMapper.insert(adGroupItem);
        if (count < GlobalConstant.Attribute.YES) {
            return ServiceResult.notFound();
        }
        return ServiceResult.of(count);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ServiceResult<AdGroupItem> getAdGroupItemById(Integer id) {
        AdGroupItem adGroupItem = adGroupItemMapper.selectById(id);

        if (adGroupItem == null) {
            return ServiceResult.notFound();
        }

        return ServiceResult.of(adGroupItem);
    }

    @Override
    public ServiceResult<Integer> updateAdGroupItem(AdGroupItemDto adGroupItemDto) {
        AdGroupItem adGroupItem = new AdGroupItem();
        BeanUtils.copyProperties(adGroupItemDto, adGroupItem);
        adGroupItem.setUpdateTime(new Date());
        //返回个数
        Integer count = adGroupItemMapper.updateById(adGroupItem);
        if (count < GlobalConstant.Attribute.YES) {
            return ServiceResult.notFound();
        }
        return ServiceResult.of(count);
    }

    @Override
    public ServiceResult<Integer> deleteAdGroupItemById(int id) {
        AdGroupItem adGroupItem = new AdGroupItem();
        adGroupItem.setId(id);
        adGroupItem.setIsDel(GlobalConstant.IsDel.YES);
        adGroupItem.setUpdateTime(new Date());
        Integer count = adGroupItemMapper.updateById(adGroupItem);
        if (count < GlobalConstant.Attribute.YES) {
            return ServiceResult.notFound();
        }
        return ServiceResult.of(count);
    }
}
