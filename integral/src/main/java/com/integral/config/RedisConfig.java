package com.integral.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.config
 * @date:2019/6/17 redis配置类
 * 出自:作者 很酷呦
 * 地址：https://blog.csdn.net/weixin_39723544/article/details/80743074
 **/
@Component
@EnableCaching
@Slf4j
public class RedisConfig extends CachingConfigurerSupport {


    @Autowired
    private JedisConnectionFactory factory;
//    @Autowired
//    private DateJedisConnectionFactory dateJedisConnectionFactory;

    /**
     * key生成策略
     *
     * @return KeyGenerator
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, param) -> {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(target.getClass().getName());
            stringBuilder.append(":");
            stringBuilder.append(method.getName());
            for (Object obj : param) {
                stringBuilder.append(String.valueOf(obj));
            }

            String key = String.valueOf(stringBuilder);
            log.info("自带生成key{}", key);
            return key;
        };
    }

    /**
     * 初始化redis缓冲管理器,在这里可以缓冲整体过期时间
     *
     * @return
     */
    @Bean
    @Override
    public CacheManager cacheManager() {
        log.info("-----------------------初始化cacheManager------------------------------------");
        //log.error("redis->{}",jedisConnectionFactory.getHostName());
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(factory);
        return builder.build();
    }

    /**
     * redisTemplate
     *
     * @param jedisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        log.info("---------------------------初始化redisTemplate--------------------------------");
        /*设置序列化*/
        Jackson2JsonRedisSerializer jsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jsonRedisSerializer.setObjectMapper(objectMapper);
        /*配置redisTemplate*/
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        RedisSerializer redisSerializer = new StringRedisSerializer();
        /*key序列化*/
        redisTemplate.setKeySerializer(redisSerializer);
        /*string value序列化*/
        redisTemplate.setValueSerializer(jsonRedisSerializer);
        /*hash key序列化*/
        redisTemplate.setHashKeySerializer(redisSerializer);
        /*hash value序列化*/
        redisTemplate.setHashValueSerializer(jsonRedisSerializer);
        return redisTemplate;
    }

    @Bean
    @Override
    public CacheErrorHandler errorHandler() {
        // 当redis异常 程序正常走 记录日志
        CacheErrorHandler cacheErrorHandler = new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
                log.error("redis获取值异常{} key->{}", e.toString(), key);
            }

            @Override
            public void handleCachePutError(RuntimeException e, Cache cache, Object o, Object key) {
                log.error("redis插入值异常->{},key->{}", e.toString(), key);
            }

            @Override
            public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {
                log.error("redis删除异常->{},key->{}", e.toString(), key);
            }

            @Override
            public void handleCacheClearError(RuntimeException e, Cache cache) {
                log.error("redis清除缓冲异常->{}", e.toString());
            }
        };
        return cacheErrorHandler;
    }
}

