package com.angel.provider.job;

import com.angel.core.utils.RedisSetService;
import com.angel.provider.mapper.BlogArticleMapper;
import com.angel.provider.model.domain.BlogArticle;
import com.angel.util.RedisKeyUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * 文章定时任务
 * @Author angel
 * @Date 19-5-4
 */
@Component
@Slf4j
public class ArticleTask {

    @Resource
    private BlogArticleMapper blogArticleMapper;

    @Resource
    private RedisSetService redisSetService;

    /**
     * 更新浏览数
     */
    @Scheduled(cron = "0 0 1 * * ?")    //每天凌晨1点执行
    public void updateArticleBrowse() {
        log.info("更新文章浏览数定时任务开始执行");

        // 存储list实体
        List<BlogArticle> list = Lists.newArrayList();
        // 获取set集合
        Set<ZSetOperations.TypedTuple<String>> typedTupleSet =
                redisSetService.zRange(RedisKeyUtil.getArticleBrowseCount());
        for (ZSetOperations.TypedTuple<String> item : typedTupleSet) {
            // value
            Integer id = Integer.parseInt(item.getValue());
            // score
            Long score = item.getScore().longValue();

            BlogArticle blogArticle = new BlogArticle();
            blogArticle.setId(id);
            blogArticle.setBrowseCount(score);
            // 将实体加入list
            list.add(blogArticle);
        }
        blogArticleMapper.updateBrowseCountList(list);
    }
}
