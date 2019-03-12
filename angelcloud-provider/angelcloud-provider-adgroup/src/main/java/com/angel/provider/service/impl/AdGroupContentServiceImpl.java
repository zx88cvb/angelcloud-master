package com.angel.provider.service.impl;

import com.angel.base.constant.GlobalConstant;
import com.angel.base.service.ServiceResult;
import com.angel.provider.mapper.AdGroupContentMapper;
import com.angel.provider.model.domain.AdGroupContent;
import com.angel.provider.model.dto.AdGroupContentDto;
import com.angel.provider.model.vo.AdGroupContentVo;
import com.angel.provider.service.IAdGroupContentService;
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
public class AdGroupContentServiceImpl extends ServiceImpl<AdGroupContentMapper, AdGroupContent> implements IAdGroupContentService{

    @Resource
    private AdGroupContentMapper adGroupContentMapper;
    
    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ServiceResult<Page<AdGroupContentVo>> getAdGroupContentPage(AdGroupContentDto adGroupContentDto) {
        Page<AdGroupContentVo> page = new Page<>();
        //条件查询
        LambdaQueryWrapper<AdGroupContent> entity = new QueryWrapper<AdGroupContent>().lambda()
                .eq(AdGroupContent:: getIsDel, GlobalConstant.IsDel.NO)
                .orderByDesc(AdGroupContent:: getCreateTime);

        //判断广告组外键为不为空
        if (adGroupContentDto.getGroupId() != null) {
            entity.eq(AdGroupContent:: getGroupId, adGroupContentDto.getGroupId());
        }

        //判断广告项外键为不为空
        if (adGroupContentDto.getItemId() != null) {
            entity.eq(AdGroupContent:: getItemId, adGroupContentDto.getItemId());
        }

        // 判断源内容不为空
        if (adGroupContentDto.getSourceId() != null) {
            entity.eq(AdGroupContent:: getSourceId, adGroupContentDto.getSourceId());
        }

        IPage<AdGroupContent> iPageAdGroupContent = adGroupContentMapper.
                selectPage(new Page<>(adGroupContentDto.getPageNum(), adGroupContentDto.getPageSize()), entity);

        // 获取集合对象
        List<AdGroupContent> adGroupContentList = iPageAdGroupContent.getRecords();

        //将AdGroup 转换成Vo对象
        List<AdGroupContentVo> collect = adGroupContentList.stream().map(e -> {
            AdGroupContentVo AdGroupContentVo = new AdGroupContentVo();
            BeanUtils.copyProperties(e, AdGroupContentVo);
            return AdGroupContentVo;
        }).collect(Collectors.toList());

        BeanUtils.copyProperties(iPageAdGroupContent, page);
        page.setRecords(collect);


        return ServiceResult.of(page);
    }

    @Override
    public ServiceResult<Integer> insertAdGroupContent(AdGroupContentDto adGroupContentDto) {
        AdGroupContent adGroupContent = new AdGroupContent();
        BeanUtils.copyProperties(adGroupContentDto, adGroupContent);
        //返回个数
        Integer count = adGroupContentMapper.insert(adGroupContent);
        if (count < GlobalConstant.Attribute.YES) {
            return ServiceResult.notFound();
        }
        return ServiceResult.of(count);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ServiceResult<AdGroupContentDto> getAdGroupContentById(Integer id) {
        AdGroupContent adGroupContent = adGroupContentMapper.selectById(id);

        if (adGroupContent == null) {
            return ServiceResult.notFound();
        }

        AdGroupContentDto adGroupContentDto = new AdGroupContentDto();
        BeanUtils.copyProperties(adGroupContent, adGroupContentDto);

        return ServiceResult.of(adGroupContentDto);
    }

    @Override
    public ServiceResult<Integer> updateAdGroupContent(AdGroupContentDto adGroupContentDto) {
        AdGroupContent adGroupContent = new AdGroupContent();
        BeanUtils.copyProperties(adGroupContentDto, adGroupContent);
        adGroupContent.setUpdateTime(new Date());
        //返回个数
        Integer count = adGroupContentMapper.updateById(adGroupContent);
        if (count < GlobalConstant.Attribute.YES) {
            return ServiceResult.notFound();
        }
        return ServiceResult.of(count);
    }

    @Override
    public ServiceResult<Integer> deleteAdGroupContentById(int id) {
        AdGroupContent adGroupContent = new AdGroupContent();
        adGroupContent.setId(id);
        adGroupContent.setIsDel(GlobalConstant.IsDel.YES);
        adGroupContent.setUpdateTime(new Date());
        Integer count = adGroupContentMapper.updateById(adGroupContent);
        if (count < GlobalConstant.Attribute.YES) {
            return ServiceResult.notFound();
        }
        return ServiceResult.of(count);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ServiceResult<AdGroupContent> selectById(Integer id) {
        AdGroupContent adGroupContent = adGroupContentMapper.selectByAllId(id);
        if (adGroupContent == null) {
            return ServiceResult.notFound();
        }
        return ServiceResult.of(adGroupContent);
    }
}
