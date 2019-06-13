package com.integral.util;

import org.apache.commons.lang.StringUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {


    /**
     * md5加密
     * @param pwd
     * @return
     */
    public static String md5(String pwd) {
        if (StringUtils.isNotBlank(pwd)) {
            byte[] secretBytes = null;

            try {
                //生成一个md5加密摘要
                MessageDigest md = MessageDigest.getInstance("MD5");
                //对字符穿进行加密
                md.update(pwd.getBytes());
                secretBytes = md.digest();

            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("没有这个加密算法");
            }

            //将加密后的数据转换成16进制
            String md5code = new BigInteger(1, secretBytes).toString();
            //果生成数字未满32位，需要前面补0
      /*  for(int i=0;i<md5code.length();i++){
            md5code="0"+md5code;
        }*/
            return md5code;
        }
        return null;
    }

}
