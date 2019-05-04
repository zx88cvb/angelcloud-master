package com.angel.core.utils;

import com.angel.util.PublicUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * redis hash
 * @Author angel
 * @Date 19-4-28
 */
@Component
@Slf4j
public class RedisHashService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 根据key获取给定字段值
     * @param key key
     * @param fields 指定字段
     * @param <T> 泛型
     * @return list集合
     */
    public <T> List<T> getValueByFields(String key, Set<String> fields) {
        HashOperations<String, String, T> hash = redisTemplate.opsForHash();
        if (!redisTemplate.hasKey(key)) {
            return Collections.emptyList();
        }
        List<T> values = hash.multiGet(key, fields);
        if (PublicUtil.isEmpty(values)) {
            return Collections.emptyList();
        }
        log.info("getValueByFields - 根据key获取所有给定字段的值. [OK] key={}, fields={}, values={}", key, fields, values);
        return values;

    }

    /**
     * 根据key获取字段
     * @param key key
     * @param field 字段
     * @param <T> 泛型
     * @return list集合
     */
    public <T> List<T> getValueByField(String key, String field) {
        HashOperations<String, String, T> hash = redisTemplate.opsForHash();
        if (!redisTemplate.hasKey(key)) {
            return Collections.emptyList();
        }
        T value = hash.get(key, field);
        if (PublicUtil.isEmpty(value)) {
            return Collections.emptyList();
        }
        List<T> values = Lists.newArrayList();
        values.add(value);
        log.info("getValueByField - 根据key获取给定字段的值. [OK] key={}, field={}, values={}", key, field, values);
        return values;
    }

    public void setValueByFields(String key, Map<String, Object> map) {
        HashOperations<String, String, Object> hash = redisTemplate.opsForHash();
        hash.putAll(key, map);
        log.info("setValueByFields - 同时将多个 field-value (域-值)对设置到哈希表 key 中. [ok] key={}, map={}", key, map);
    }

    /**
     * 删除一个或多个哈希表字段
     * @param key key
     * @param hashKeys 一个或多个hash字段
     * @return 删除个数
     */
    public Long removeFields(String key, String... hashKeys) {
        HashOperations<String, String, Object> hash = redisTemplate.opsForHash();
        Long result = hash.delete(key, (Object) hashKeys);
        log.info("removeFields- 删除一个或多个哈希表字段. [OK] key={}, hashKeys={}, result={}", key, hashKeys, result);
        return result;
    }

}
