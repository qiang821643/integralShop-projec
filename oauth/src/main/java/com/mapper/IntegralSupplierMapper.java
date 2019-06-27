package com.mapper;


import com.model.IntegralSupplier;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IntegralSupplierMapper extends BaseMapper<IntegralSupplier>{

    /**
     * 查找供应商
     * @param userName
     * @return
     */
    IntegralSupplier queryByUserName(@Param("userName") String userName);

    /**
     * 更改密码
     * @param userName
     */
    void updatePwdByUserName(String userName, String pwd);

    /**
     * 插入数据
     * @param integralSupplier
     */
    void insertSuppiler(IntegralSupplier integralSupplier);
}
