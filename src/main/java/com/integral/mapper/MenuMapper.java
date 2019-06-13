package com.integral.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.mapper
 * @date:2019/5/18
 **/
@Mapper
public interface MenuMapper {

    public List<Map<String,Object>> test();
}
