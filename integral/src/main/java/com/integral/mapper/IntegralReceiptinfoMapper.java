package com.integral.mapper;


import com.integral.model.IntegralReceiptinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.mapper
 * @date:2019/5/31
 **/
@Mapper
public interface IntegralReceiptinfoMapper extends BaseMapper<IntegralReceiptinfo>{

    @Select("select SEQ_INTEGRAL_RECEIPTINFO.nextval id from dual")
    public Integer querySEQ();
}
