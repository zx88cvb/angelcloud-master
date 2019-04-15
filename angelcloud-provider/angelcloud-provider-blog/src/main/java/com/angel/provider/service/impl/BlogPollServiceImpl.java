package com.angel.provider.service.impl;

import com.angel.provider.mapper.BlogPollMapper;
import com.angel.provider.model.domain.BlogPoll;
import com.angel.provider.service.IBlogPollService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author angel
 * @Date 19-4-15
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BlogPollServiceImpl extends ServiceImpl<BlogPollMapper, BlogPoll>
        implements IBlogPollService {
}
