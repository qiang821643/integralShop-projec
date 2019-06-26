package com.integral.mapper;

import com.integral.model.IntegralMemberCcs;
import com.integral.model.IntegralitemCcs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.mapper
 * @date:2019/5/28
 **/
@Mapper
public interface IntegralitemCssMapper extends BaseMapper<IntegralitemCcs>{

    @Select("select SEQ_INTEGRAL_ITEM_CCS.NEXTVAL id FROM DUAL")
    public Integer querySEQ();


}
