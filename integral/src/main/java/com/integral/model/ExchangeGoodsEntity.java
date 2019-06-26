package com.integral.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.model
 * @date:2019/5/28
 **/
@Data
public class ExchangeGoodsEntity implements Serializable {

    private Integer goodsId;

    private int count;

    private IntegralReceiptinfo integralReceiptinfo;
}
