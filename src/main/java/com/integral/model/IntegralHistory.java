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
public class IntegralHistory implements Serializable {

    private Integer id;

    private String points;

    private String action;

    private Integer integral_member_ccs_id;

    private Date create_date;

    private Integer integral_item_id;
}
