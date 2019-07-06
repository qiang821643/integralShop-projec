package com.integral.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.model
 * @date:2019/5/31
 **/
@Data
public class RegisterEnttity {




    @NotNull(message = "账号不能为空")
    private String user_name;

    @NotNull(message = "密码不能为空")
    private String pwd;

    @NotNull(message = "地址不能为空")
    private String address;

    @NotNull(message = "负责人不能为空")
    private String person_liable;

    @NotNull(message = "电话不能为空")
    private String phone;

    private String email;

    private String supplier_name;
}
