package com.integral.mapper;

import com.integral.model.Integralitem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.mapper
 * @date:2019/5/28
 **/
@Mapper
public interface IntegralitemMapper extends BaseMapper<Integralitem>{

    @Select("select SEQ_INTEGRAL_ITEM.NEXTVAL id FROM DUAL")
    public Integer querySEQ();
}
