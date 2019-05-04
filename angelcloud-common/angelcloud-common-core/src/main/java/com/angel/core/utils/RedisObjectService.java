package com.angel.core.utils;

import com.angel.base.constant.GlobalConstant;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * redis 操作object
 * @Author angel
 * @Date 19-5-4
 */
@Component
@Slf4j
public class RedisObjectService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 根据key获取
     * @param key key
     * @return String对象
     */
    public Object getKey(String key) {
        Object value = null;
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        if (redisTemplate.hasKey(key)) {
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
        redisTemplate.delete(key);
        log.info("deleteKey. [OK] key={}", key);

    }

    /**
     * 设置key value
     * @param key key
     * @param value value
     */
    public void setKey(String key, Object value) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(key), "Redis key is not null");

        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        ops.set(key, value);
        redisTemplate.expire(key, GlobalConstant.Sys.REDIS_DEFAULT_EXPIRE, TimeUnit.MINUTES);
        log.info("setKey. [OK] key={}, value={}, expire=默认超时时间", key, value);


    }

    /**
     * 设置key value 过期时间
     * @param key key
     * @param value value
     * @param timeout 超时时间
     * @param unit 单位
     */
    public void setKey(String key, Object value, long timeout, TimeUnit unit) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(key), "Redis key is not null");
        Preconditions.checkArgument(unit != null, "TimeUnit is not null");
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        ops.set(key, value);
        redisTemplate.expire(key, timeout, unit);
        log.info("setKey. [OK] key={}, value={}, timeout={}, unit={}", key, value, timeout, unit);

    }

}
