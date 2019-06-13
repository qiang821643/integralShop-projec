package com.integral.util;

import java.util.Date;
import java.util.Random;

/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.util
 * @date:2019/6/5
 **/
public class NumberUtil {


    public static String createCode(String id) {

        String time = TimeUtil.foramt(TimeUtil.YYYYMMMDD, new Date());
        Random random = new Random();
        String resulr = "";
        for (int i=0;i<3;i++){
            resulr += random.nextInt(10);
        }
        return resulr+time+id;
    }
}
