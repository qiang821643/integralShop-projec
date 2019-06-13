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
public class Integralitem implements Serializable {

    private Integer id;

    private Integer integral_goods_id;

    private Integer integral_member_ccs_id;

    private String pay_points;

    private String status;

    private String item_code;

    private Date create_date;

    private Date update_date;

    private String remark;
}
