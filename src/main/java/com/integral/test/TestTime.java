package com.integral.test;

import com.integral.model.IntegralGoods;
import com.integral.model.IntegralSupplier;
import com.integral.util.TimeUtil;
import org.apache.commons.beanutils.BeanUtils;

import java.util.Date;

/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.test
 * @date:2019/5/25
 **/
public class TestTime {

    public static void main(String[] args) {

        IntegralGoods goods = new IntegralGoods();
        goods.setId(1);
        goods.setStatus("01");
        goods.setCount("50");
        goods.setRemark("fds");
        IntegralGoodsCss supplier = new IntegralGoodsCss();
        try {
            BeanUtils.copyProperties(supplier, goods);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(supplier);
    }


}
