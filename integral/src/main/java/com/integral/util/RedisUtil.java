package com.integral.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.util
 * @date:2019/6/17 redis工具
 **/
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @param key
     * @param value
     * @return
     */
    public Integer insert(Object key, Object value) {
        if (value != null) {
            return redisTemplate.opsForValue().append(key, String.valueOf(value));
        }
        return 0;
    }

    /**
     * @param key
     * @return
     */
    public Object getValueByRedis(Object key) {
        if (key != null) {
            return redisTemplate.opsForValue().get(key);
        }
        return null;
    }

    /**
     * 删除
     *
     * @param key
     */
    public void delete(Object key) {

        if (key != null) {
            redisTemplate.delete(key);
        }
    }

    /**
     * hash 插入
     *
     * @param key
     * @param value
     */
    public void putHash(Object key, Map<Object, String> value) {
        if (key != null) {
            redisTemplate.opsForHash().putAll(key, value);
        }
    }

    /**
     * @param key
     * @return
     */
    public Map<String, Object> getHashByRedis(Object key) {
        if (key != null) {
            return redisTemplate.opsForHash().entries(key);
        }
        return null;
    }

    /**
     * 判断值是否存在
     *
     * @return
     */
    public boolean exists(Object key, Object mapKey) {
        if (key != null && mapKey != null) {
            return redisTemplate.opsForHash().hasKey(key,mapKey);
        }
        return false;
    }


    /**
     * @param key
     */
    public void deleteHash(Object key) {
        if (key != null) {
            redisTemplate.opsForHash().delete(key);
        }
    }

    /**
     *
     * @param key
     * @param mapKey
     */
    public void deleteHash(Object key, Object mapKey){
        if(key != null && mapKey != null){
            redisTemplate.opsForHash().delete(key,mapKey);
        }
    }
}
