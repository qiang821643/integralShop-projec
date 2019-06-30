package com.oauth.supermarket.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 积分会员
 */

@Data
public class IntegralSupplier implements Serializable {

    private Integer id;

    @NotNull(message = "账号不能为空")
    private String user_name;

    @NotNull(message = "密码不能为空")
    private String pwd;

    private String supplier_name;
    private String points_count;

    @NotNull(message = "地址不能为空")
    private String address;

    @NotNull(message = "辅助人不能为空")
    private String person_liable;

    @NotNull(message = "电话不能为空")
    private String phone;

    private String status;

    private Date create_date;

    private String email;
}


