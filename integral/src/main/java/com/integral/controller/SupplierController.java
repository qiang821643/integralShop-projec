package com.integral.controller;

import com.integral.constant.Constant;
import com.integral.mapper.IntegralSupplierMapper;
import com.integral.model.Result;
import com.integral.service.UserNameService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Contact;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;
import com.integral.model.IntegralSupplier;
import com.integral.model.RegisterEnttity;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;


@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private UserNameService userNameService;
    @Autowired
    private IntegralSupplierMapper supplierMapper;

    @ApiOperation(value = "修改密码",notes = "")
    @GetMapping("/update")
    public Result updatePwd(@RequestParam(name = "userName") @NotNull(message = "用户名不能为空") String userName,
                            @RequestParam(name = "newPwd") @NotNull(message = "新密码不能为空") String newPwd,
                            @RequestParam(name = "oldPwd") @NotNull(message = "老密码不能为空") String oldPwd) {

        return userNameService.updatePwd(userName, newPwd, oldPwd);
    }

    @PostMapping("/register")
    public Result registerSupplier(@RequestBody @Valid RegisterEnttity registerEnttity) {

        return userNameService.register(registerEnttity);
    }

    @ApiOperation(value = "",notes = "")
    @GetMapping("/getUserName")
    public Result getUserName(@RequestParam(name = "userName") String userName) {

        return userNameService.getUserName(userName);
    }



}
