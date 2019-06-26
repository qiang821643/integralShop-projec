package com.integral.mapper;

import com.integral.model.IntegralFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.mapper
 * @date:2019/5/27
 **/
@Mapper
public interface IntegralFileMapper extends BaseMapper<IntegralFile>{


    //public void insrt(IntegralFile integralFile);

    @Select("select SEQ_INTEGRAL_File.Nextval id from dual")
    public Integer querySEQ();
}
