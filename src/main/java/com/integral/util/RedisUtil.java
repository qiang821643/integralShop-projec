package com.integral.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.util
 * @date:2019/6/17
 **/
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    public String insert(){

        redisTemplate.opsForValue().append("red","12");
        return null;
    }
}
