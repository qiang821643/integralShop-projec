package com.integral.mapper;

import com.integral.model.IntegralSpecifications;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.mapper
 * @date:2019/5/27
 **/
@Mapper
public interface IntegralSpecificationsMapper extends BaseMapper<IntegralSpecifications>{

    @Select("select SEQ_INTEGRAL_SPECIFICATIONS.nextval id from dual")
    public Integer querySEQ();
}
