package com.integral.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 积分会员
 */

@Data
public class IntegralMemberCcs implements Serializable {

    private Integer id;

    private String z_member_id;

    private Integer count_points;

    private Integer enable_points;

    private Date create_date;

    private String status;
}


