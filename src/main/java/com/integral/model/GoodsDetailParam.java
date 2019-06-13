package com.integral.model;


import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class GoodsDetailParam implements Serializable {

    private IntegralGoods goods;

    private List<Map<String,Object>> specifications;

    private String sunPath;

    private List<String> parentPath;
}
