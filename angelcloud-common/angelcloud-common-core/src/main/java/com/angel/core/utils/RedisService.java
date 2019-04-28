package com.angel.core.utils;

import com.angel.base.constant.GlobalConstant;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * redis 操作
 * @Author angel
 * @Date 19-4-28
 */
@Component
@Slf4j
public class RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 根据key获取
     * @param key key
     * @return String对象
     */
    public String getKey(String key) {
        String value = null;
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        if (stringRedisTemplate.hasKey(key)) {
            value = ops.get(key);
        }
        log.info("getKey. [OK] key={}, value={}", key, value);
        return value;
    }

    /**
     * 根据key删除
     * @param key key
     */
    public void deleteKey(String key) {
        stringRedisTemplate.delete(key);
        log.info("deleteKey. [OK] key={}", key);

    }

    /**
     * 设置key value
     * @param key key
     * @param value value
     */
    public void setKey(String key, String value) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(key), "Redis key is not null");

        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set(key, value);
        stringRedisTemplate.expire(key, GlobalConstant.Sys.REDIS_DEFAULT_EXPIRE, TimeUnit.MINUTES);
        log.info("setKey. [OK] key={}, value={}, expire=默认超时时间", key, value);


    }

    /**
     * 设置key value 过期时间
     * @param key key
     * @param value value
     * @param timeout 超时时间
     * @param unit 单位
     */
    public void setKey(String key, String value, long timeout, TimeUnit unit) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(key), "Redis key is not null");
        Preconditions.checkArgument(unit != null, "TimeUnit is not null");
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set(key, value);
        stringRedisTemplate.expire(key, timeout, unit);
        log.info("setKey. [OK] key={}, value={}, timeout={}, unit={}", key, value, timeout, unit);

    }

}
