package com.integral.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.model
 * @date:2019/5/27
 * 规格表
 **/
@Data
public class IntegralSpecifications implements Serializable {

    private Integer id;

    private String specifications_name;

    private Integer goods_type;

   private Integer goods_level;

}
