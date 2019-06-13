package com.integral.security;

import com.nimbusds.jwt.JWT;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;


/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.security
 * @date:2019/6/3
 **/
@Slf4j
public class JwtTokenUtil {

    private final String key = "integralShop";

    /*static {
        try{
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(inputStream, "123456".toCharArray());
            privateKey =(PrivateKey) keyStore.getKey("integralShop","123456".toCharArray());//jwt 为 命令生成整数文件时的别名
            publicKey =  keyStore.getCertificate("integralShop").getPublicKey();

        }catch (Exception e){
            e.printStackTrace();
        }
    }*/

    public static String generateToken(String subject, String salt,Integer id) {
//        return Jwts.builder()
//                .setClaims(null)
//                .setSubject(subject)
//                .setExpiration(new Date(System.currentTimeMillis() + expirationSeconds * 1000))
//                .signWith(SignatureAlgorithm.HS512, "integralShop") // 不使用公钥私钥
//                .signWith(SignatureAlgorithm.RS256, "integralShop")
//                .compact();


        long nowMillis = System.currentTimeMillis();
        System.out.println(nowMillis);
        Date nowData = new Date(nowMillis);
        Date nowData1 = new Date(System.currentTimeMillis()+259200l*1000);
        Date nowData2 = new Date(System.currentTimeMillis()-259200l*1000);
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


        log.info("nowData:"+nowData+",nowData1:"+nowData1);
        return builder.compact();
    }

    /**
     * 解析令牌
     * @param token
     * @return
     */
    public static Claims parseToken(String token) {
//        String subject = null;
//        try {
//            Claims claims = Jwts.parser()
//                   .setSigningKey(salt) // 不使用公钥私钥
//                    .setSigningKey("integralShop")
//                    .parseClaimsJws(token)
//                    .getBody();
//            subject = claims.getSubject();
//        } catch (Exception e) {
//        }
//        return subject;
        log.info(token);
        return  Jwts.parser()
                .setSigningKey("integralShop")
               .parseClaimsJws(token)
                .getBody();


    }




}
