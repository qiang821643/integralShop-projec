package com.integral.controller;

import com.integral.model.IntegralReceiptinfo;
import com.integral.model.Result;
import com.integral.service.base.MemberCssService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.controller
 * @date:2019/5/29
 **/
@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberCssService memberCssService;

    @ApiOperation(value = "同步账户",notes = "")
    @GetMapping("/synchronousUser")
    public Result synchronousUser(@RequestParam (name = "member") @NotNull(message = "账号不能为空") String member){

        return memberCssService.synUserName(member);
    }

    @ApiOperation(value = "用户积分",notes = "")
    @GetMapping("/userPoints")
    public Result getUserPoints(@RequestParam @NotNull(message = "用户id不能为空") Integer id){

        return memberCssService.pointsInfo(id);
    }

    @ApiOperation(value = "兑换积分",notes = "")
    @GetMapping("/exchangePoints")
    public Result exchangePoints(@RequestParam("id") @NotNull(message = "没有当前用户") Integer id,
                                 @RequestParam("coin") @NotNull(message = "当前没有平台币") Integer coin){

        return memberCssService.exchangePoints(id,coin);
    }

    @ApiOperation(value = "用户收件地址",notes = "")
    @GetMapping("/getAddresss")
    public Result getAddresss(@RequestParam() Integer id){

        return memberCssService.userAdress(id);
    }

    @ApiOperation(value = "添加收件信息",notes = "")
    @PostMapping("/addReceiptinfo")
    public Result addReceiptinfo(@RequestBody IntegralReceiptinfo receiptinfo){

        return memberCssService.addAddress(receiptinfo);
    }
}
