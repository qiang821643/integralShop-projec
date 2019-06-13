package com.integral.model;

import lombok.Data;

import java.util.Date;

/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.model
 * @date:2019/5/28
 **/
@Data
public class IntegralitemCcs {

    private Integer id;

    private Integer integral_item_id;

    private String count;

    private String addressee;

    private String address;

    private String phone;

    private String sender;

    private String sender_phone;

    private String sender_address;

    private String postage;

    private String logistics_company;

    private String courier_number;

    private Date create_date;

    private Date update_date ;
}
