package com.integral.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.mapper
 * @date:2019/5/28
 **/
public interface BaseMapper<T> {

    public void insert(T t);

    public void update(T t);

    public void delete(@Param("id") Integer id);

    public void deleteBatch(List<Integer> ids);

    public List<T> findByParam(T t);

    public T findOne(T t);
}
