package com.angel.core.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * redis SET
 * @Author angel
 * @Date 19-5-4
 */
@Component
@Slf4j
public class RedisSetService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public Set<String> getAllValue(String key) {
        Set<String> result;
        SetOperations<String, String> setOps = redisTemplate.opsForSet();
        result = setOps.members(key);
        log.info("getAllValue - 根据key获取元素. [OK] key={}, value={}", key, result);
        return result;
    }

    public Long add(String key, String... value) {
        SetOperations<String, String> setOps = redisTemplate.opsForSet();
        Long result = setOps.add(key, value);
        log.info("add - 向key里面添加元素, key={}, value={}, result={}", key, value, result);
        return result;
    }

    public Long remove(String key, String... value) {
        SetOperations<String, String> setOps = redisTemplate.opsForSet();
        Long result = setOps.remove(key, (Object) value);
        log.info("remove - 根据key移除元素, key={}, value={}, result={}", key, value, result);
        return result;
    }

    public boolean zAdd(String key, String value, double score) {
        ZSetOperations<String, String> setOps = redisTemplate.opsForZSet();
        Boolean result = setOps.add(key, value, score);
        log.info("add - 向key里面添加元素, key={}, value={}, result={}, score={}", key, value, result, score);
        return result;
    }

    public Double zIncr(String key, String value, double score) {
        ZSetOperations<String, String> setOps = redisTemplate.opsForZSet();
        Double result = setOps.incrementScore(key, value, score);
        log.info("add - 向key里面incr元素, key={}, value={}, result={}, score={}", key, value, result, score);
        return result;
    }

    /**
     * zset 根据key value获取分数
     * @param key key
     * @param value value
     * @return 分数
     */
    public Double zScore(String key, String value) {
        ZSetOperations<String, String> setOps = redisTemplate.opsForZSet();
        Double result = setOps.score(key, value);
        log.info("add - 根据key value获取分数, key={}, value={}, result={}", key, value, result);
        return result;
    }

    /**
     * 根据key获取全部Set集合
     * @param key key
     * @return Set集合
     */
    public Set<ZSetOperations.TypedTuple<String>> zRange(String key) {
        ZSetOperations<String, String> setOps = redisTemplate.opsForZSet();
        Set<ZSetOperations.TypedTuple<String>> typedTuples = setOps.rangeWithScores(key, 0, -1);
        log.info("add - 根据key获取全部Set集合, key={}, typedTuples={}", key, typedTuples);
        return typedTuples;
    }
}
