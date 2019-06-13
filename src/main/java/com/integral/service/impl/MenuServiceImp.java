package com.integral.service.impl;

import com.integral.mapper.MenuMapper;
import com.integral.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.service.impl
 * @date:2019/5/18
 **/
@Service
public class MenuServiceImp implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Map<String, Object>> testData() {
        return menuMapper.test();
    }
}
