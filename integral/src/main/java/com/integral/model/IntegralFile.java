package com.integral.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.model
 * @date:2019/5/27
 **/
@Data
public class IntegralFile implements Serializable {

    private Integer id;

    private String file_path;

    private String status;

    private Integer sun_id;

    private Date create_date;

    private Integer goods_id;
}
