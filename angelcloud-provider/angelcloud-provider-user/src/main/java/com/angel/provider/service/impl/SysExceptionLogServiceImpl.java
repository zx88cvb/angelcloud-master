package com.angel.provider.service.impl;

import com.angel.provider.mapper.SysExceptionLogMapper;
import com.angel.provider.model.domain.SysExceptionLog;
import com.angel.provider.model.dto.GlobalExceptionLogDto;
import com.angel.provider.service.ISysExceptionLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author: Angel
 * @Date: 2018/10/17.
 * @Description:
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class SysExceptionLogServiceImpl extends ServiceImpl<SysExceptionLogMapper, SysExceptionLog> implements ISysExceptionLogService {

    @Resource
    private SysExceptionLogMapper sysExceptionLogMapper;

    @Override
    public void insertSendExceptionLog(GlobalExceptionLogDto globalExceptionLogDto) {
        SysExceptionLog sysExceptionLog = new SysExceptionLog();
        BeanUtils.copyProperties(globalExceptionLogDto,sysExceptionLog);
        sysExceptionLogMapper.insert(sysExceptionLog);
    }
}
