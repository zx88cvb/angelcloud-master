package com.angel.provider.service.impl;

import com.angel.base.constant.GlobalConstant;
import com.angel.base.enums.ErrorCodeEnum;
import com.angel.base.service.ServiceResult;
import com.angel.provider.mapper.AdGroupContextMapper;
import com.angel.provider.model.domain.AdGroupContext;
import com.angel.provider.model.dto.AdGroupContextDto;
import com.angel.provider.service.IAdGroupContextService;
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

/**
 * 广告内容详情
 * @Author angel
 * @Date 19-3-4
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class AdGroupContextServiceImpl extends ServiceImpl<AdGroupContextMapper, AdGroupContext>
        implements IAdGroupContextService {

    @Resource
    private AdGroupContextMapper adGroupContextMapper;


    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ServiceResult<Page<AdGroupContext>> getConditionPage(AdGroupContextDto adGroupContextDto) {
        Page<AdGroupContext> page = new Page<>();
        //条件查询
        LambdaQueryWrapper<AdGroupContext> entity = new QueryWrapper<AdGroupContext>().lambda()
                .eq(AdGroupContext:: getIsDel, GlobalConstant.IsDel.NO)
                .orderByDesc(AdGroupContext:: getCreateTime);

        // 判断标题不为空
        if (StringUtils.isNotBlank(adGroupContextDto.getTitle())) {
            entity.like(AdGroupContext:: getTitle, adGroupContextDto.getTitle());
        }

        // 查询
        IPage<AdGroupContext> iPageAdGroupContent = adGroupContextMapper.
                selectPage(new Page<>(adGroupContextDto.getPageNum(), adGroupContextDto.getPageSize()), entity);

        if (iPageAdGroupContent == null) {
            return ServiceResult.errorByMessage(ErrorCodeEnum.GL99990500.msg());
        }
        return ServiceResult.of(page);
    }

    @Override
    public ServiceResult<Integer> insert(AdGroupContextDto adGroupContextDto) {
        AdGroupContext adGroupContext = new AdGroupContext();
        BeanUtils.copyProperties(adGroupContextDto, adGroupContext);
        //返回个数
        Integer count = adGroupContextMapper.insert(adGroupContext);
        if (count < GlobalConstant.Attribute.YES) {
            return ServiceResult.notFound();
        }
        return ServiceResult.of(count);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ServiceResult<AdGroupContext> selectById(Integer id) {
        AdGroupContext adGroupContext = adGroupContextMapper.selectById(id);

        if (adGroupContext == null) {
            return ServiceResult.notFound();
        }
        return ServiceResult.of(adGroupContext);
    }

    @Override
    public ServiceResult<Integer> update(AdGroupContextDto adGroupContextDto) {
        AdGroupContext adGroupContext = new AdGroupContext();
        BeanUtils.copyProperties(adGroupContextDto, adGroupContext);
        adGroupContext.setUpdateTime(new Date());
        //返回个数
        Integer count = adGroupContextMapper.updateById(adGroupContext);
        if (count < GlobalConstant.Attribute.YES) {
            return ServiceResult.notFound();
        }
        return ServiceResult.of(count);
    }

    @Override
    public ServiceResult<Integer> deleteById(int id) {
        AdGroupContext adGroupContext = new AdGroupContext();
        adGroupContext.setId(id);
        adGroupContext.setIsDel(GlobalConstant.IsDel.YES);
        adGroupContext.setUpdateTime(new Date());
        Integer count = adGroupContextMapper.updateById(adGroupContext);
        if (count < GlobalConstant.Attribute.YES) {
            return ServiceResult.notFound();
        }
        return ServiceResult.of(count);
    }
}
