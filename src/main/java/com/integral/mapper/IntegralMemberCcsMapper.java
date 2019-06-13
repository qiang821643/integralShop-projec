package com.integral.mapper;

import com.integral.model.IntegralMemberCcs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface IntegralMemberCcsMapper extends BaseMapper<IntegralMemberCcs>{

    //IntegralMemberCcs queryByUserName(String userName);

    /**
     * 查询z_member表
     * @param maccount
     * @return
     */
    public Map<String,Object> queryZmemberTable(@Param("member") String member);

    @Select("select SEQ_INTEGRAL_MEMBER_CCS.NEXTVAL FROM dual")
    public Integer queryQUE();

    /**
     * 根据id去查询,结果集只能是1条
     * @param id
     * @return
     */
    public IntegralMemberCcs findMemberCcs(@Param("id") Integer id);

    /**
     * 减平台币
     * @param coin
     */
    public void minusCoin(Map<String,Object> param);
}
