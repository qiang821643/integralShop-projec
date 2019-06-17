package com.integral.controller;

import com.integral.service.MenuService;
import com.integral.util.FileUtils;
import com.integral.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.controller
 * @date:2019/5/18
 **/
@RestController
@RequestMapping("/menu")
public class MenuContoller {

    @Autowired
    private MenuService menuService;

    @Autowired
    private FileUtils fileUtils;

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/test1")
    public List<Map<String, Object>> test() {

        return menuService.testData();
    }

    @GetMapping("/test")
    public String test1() {
        return "服务器已经启动";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam(name = "file")  MultipartFile file)throws  IOException{

        return fileUtils.upload(file);
    }

    @GetMapping("/redis")
    public void redis(){
        redisUtil.insert();
    }
}
