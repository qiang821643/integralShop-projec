package com.integral.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.model
 * @date:2019/5/28
 **/
@Data
public class IntegralReceiptinfo implements Serializable {

    private Integer id;

    private Integer integral_member_ccs_id;

    private String address;

    private String phone;

    private String addressee;

    private String postal_code;

    private Date create_date;

    private String status;
}
