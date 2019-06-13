package com.integral.constant;


/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.constant
 * @date:2019/6/5
 **/

public enum PayEnum {

    PAY_POINTS("积分支付", "0"), PAY_COIN("平台币支付", "1"), PAY_MONEY("现金支付", "2"),
    ;

    private String name;
    private String code;

    private PayEnum(String name, String code) {
        this.name = name;
        this.code = code;
    }

    
}
