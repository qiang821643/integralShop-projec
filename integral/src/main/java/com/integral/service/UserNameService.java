package com.integral.service;

import com.integral.model.RegisterEnttity;
import com.integral.model.Result;

public interface UserNameService {

    /**
     * 密码验证
     *
     * @param userName
     * @param pwd
     * @return
     */
    public Result checkPwd(String userName, String pwd);

    /**
     * @param userName
     * @param newPwd
     * @param oldPwd
     * @return
     */
    public Result updatePwd(String userName, String newPwd, String oldPwd);

    /**
     * 注册
     *
     * @param integralSupplier
     * @return
     */
    public Result register(RegisterEnttity registerEnttity);

    /**
     * 获取账号
     *
     * @param userName
     * @return
     */
    public Result getUserName(String userName);
}
