package com.integral.controller;

import com.integral.model.AddSepci;
import com.integral.model.IntegralSpecifications;
import com.integral.model.IntegralSpecificationsValue;
import com.integral.model.Result;
import com.integral.service.SpecificationsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.controller
 * @date:2019/5/30
 **/
@RestController
@RequestMapping("/spec")
public class SpecificationsController {

    @Autowired
    private SpecificationsService specificationsService;


    @ApiOperation(value = "添加规格",notes = "")
    @PostMapping("/addSpec")
    public Result addSpec(@RequestBody AddSepci addSepci){

        return specificationsService.addSpec(addSepci);
    }
}
