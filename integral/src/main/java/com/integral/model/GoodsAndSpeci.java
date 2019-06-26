package com.integral.model;

import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;

@Data
public class GoodsAndSpeci implements Serializable {

    private Integer goods_count;

    private Integer specifications_value_Id;
}
