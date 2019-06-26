package com.integral.test;

import com.integral.util.NumberUtil;

/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.test
 * @date:2019/6/4
 **/
public class TestToken {

    public static void main(String[] args) {
        String str = NumberUtil.createCode("4");
        System.out.println(str);
    }
}
