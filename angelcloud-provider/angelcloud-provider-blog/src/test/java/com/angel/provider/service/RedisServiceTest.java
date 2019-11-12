package com.angel.provider.service;

import com.angel.ApplicationTests;
import com.angel.core.utils.RedisObjectService;
import com.angel.core.utils.RedisService;
import com.angel.provider.model.domain.BlogTag;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * @Author angel
 * @Date 19-5-4
 */
public class RedisServiceTest extends ApplicationTests {

    @Resource
    private RedisService redisService;

    @Resource
    private RedisObjectService redisObjectService;

    /*@Test
    public void testRedis() {
        redisService.setKey("test_redis_bbb", "哈哈");
        String test_redis_bbb = redisService.getKey("test_redis_bbb");
        System.out.println(test_redis_bbb);
    }

    @Test
    public void objectRedis () {
        BlogTag blogTag = new BlogTag();
        blogTag.setId(1);
        blogTag.setTagName("哈哈");
        redisObjectService.setKey("test_object_tag", blogTag);
        BlogTag domain = (BlogTag) redisObjectService.getKey("test_object_tag");
        System.out.println(domain.getTagName());
    }*/

    @Test
    public void doubleLong(){
        Double d = 12.00;
        long l = d.longValue();
        System.out.println(l);
    }

    public void hashRedis () {
        BlogTag blogTag = new BlogTag();
        blogTag.setId(1);
        blogTag.setTagName("哈哈");
    }
}
