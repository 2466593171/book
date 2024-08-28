package org.example.book.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 设置指定key的值，并设置过期时间（单位：秒）
     *
     * @param key   键
     * @param value 值
     * @param expire 过期时间（秒）
     */
    public void set(String key, Object value, long expire) {
        redisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
    }

    /**
     * 设置指定key的值（不设置过期时间）
     *
     * @param key   键
     * @param value 值
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 获取指定key的值
     *
     * @param key 键
     * @return 对应键的值
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 删除指定key
     *
     * @param key 键
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    // 示例方法：递增操作（针对整数值）
    public Long increment(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    // 示例方法：判断key是否存在
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    // 示例方法：批量删除keys
    public void deleteMultiple(String... keys) {
        redisTemplate.delete(Arrays.asList(keys));
    }

    // 可以根据需要继续添加其他对Hash、Set、List等数据结构的操作方法...
}

