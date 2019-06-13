package com.integral.model;

import lombok.Data;

import java.util.List;

/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.model
 * @date:2019/5/30
 **/
@Data
public class AddSepci {

    private String speciName;
    private Integer level;
    private Integer type;
    private List<String> value;
}
