package com.integral.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.model
 * @date:2019/5/30
 **/
@Data
public class IntegralSpecificationsValue implements Serializable {

    private Integer id;

    private String value;

    private Integer integral_specifications_id;
}
