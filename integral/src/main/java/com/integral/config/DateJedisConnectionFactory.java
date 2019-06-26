package com.integral.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.config
 * @date:2019/6/17
 * 出自:作者 很酷呦
 *  地址：https://blog.csdn.net/weixin_39723544/article/details/80743074
 **/

@ConfigurationProperties
@Configuration
@Slf4j
public class DateJedisConnectionFactory {

    /**
     * 去读yml的redis配置 创建jedisConnectionFactory 和jedispool，提供给外部初始化缓冲管理器使用
     */
    @Value("${spring.redis.host}")
    private  String host;
    @Value(("${spring.redis.port}"))
    private int port;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.jedis.pool.max-wait}")
    private int maxWaitMillis;

    @Bean
    public JedisConnectionFactory connectionFactory(){
//        此方法已经过时了不建议使用
//        JedisConnectionFactory factory = new JedisConnectionFactory();
//        factory.setHostName(host);
//        factory.setPort(port);
//        factory.setPassword(password);
//        factory.setTimeout(timeout);
        log.info("---------------------redis配置初始化-----------------------------");
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(host);
        configuration.setPort(port);
        configuration.setPassword(password);
        configuration.setDatabase(0);
        JedisClientConfiguration.JedisClientConfigurationBuilder builder = JedisClientConfiguration.builder();
        builder.connectTimeout(Duration.ofMillis(maxWaitMillis));
        JedisConnectionFactory factory = new JedisConnectionFactory(configuration);
        log.info("----------------------connectionFactory{}",factory);
        return  factory;
    }

    @Bean
    public JedisPoolConfig  jedisPool(){
        log.info("---------------------jedisPool配置初始化-----------------------------");
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);

        return jedisPoolConfig;
    }
}
