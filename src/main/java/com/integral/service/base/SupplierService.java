package com.integral.service.base;

import com.integral.model.IntegralSupplier;
import com.integral.model.Result;

public interface SupplierService {

    /**
     * 通过账号查询供应商
     * @param userName
     * @return
     */
    public IntegralSupplier queryByUserName(String userName);

    /**
     * 更改密码
     * @param userName
     */
    public void updatePwd(String userName,String pwd);

    public void inserSupplier(IntegralSupplier integralSupplier);


}
