package com.integral.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.security
 * @date:2019/6/3
 **/
@Slf4j
@Component
public class JwtTokenUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    private final String key = "integralShop";


    public String generateToken(String subject, String salt, Integer id) {


        long nowMillis = System.currentTimeMillis();
        System.out.println(nowMillis);
        Date nowData = new Date(nowMillis);
        Date nowData1 = new Date(System.currentTimeMillis() + 259200l * 1000);
        Date nowData2 = new Date(System.currentTimeMillis() - 259200l * 1000);
        Claims claims = new DefaultClaims();
        claims.setId(id.toString());
        claims.setSubject(subject);
        claims.setIssuedAt(nowData);
        claims.setExpiration(nowData1);
        claims.setNotBefore(nowData2);
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + 259200l * 1000))
                //.setIssuedAt(nowData) //设置签发时间
                .signWith(SignatureAlgorithm.HS256, "integralShop");


        log.info("nowData:" + nowData + ",nowData1:" + nowData1);
        String jwt = builder.compact();

        Object value = redisTemplate.opsForValue().get(subject);
        if (value != null) {
            redisTemplate.delete(subject);
            redisTemplate.opsForValue().append(subject, jwt);
        } else {
            redisTemplate.opsForValue().append(subject, jwt);
        }
        return jwt;
    }

    /**
     * 解析令牌
     *
     * @param token
     * @return
     */
    public Claims parseToken(String token) {

        Object value = redisTemplate.opsForValue().get(token);
        if(StringUtils.equals(token,String.valueOf(value))){
            return Jwts.parser()
                    .setSigningKey("integralShop")
                    .parseClaimsJws(token)
                    .getBody();
        }else {
            return null;
        }
    }


}
