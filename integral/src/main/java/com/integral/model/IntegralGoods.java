package com.integral.model;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class IntegralGoods implements Serializable {

    private Integer id;

    private String goods_name;

    private String price;

    private Integer integral_supplier_id;

    private String count;

    private String pay_points;

    private String remark;

    private Integer integral_file_id;

    private String status;

    private Date create_date;

    private Date update_date;

    private String type_name;

    private String postage;
}
