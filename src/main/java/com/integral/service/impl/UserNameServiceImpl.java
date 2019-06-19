package com.integral.service.impl;

import com.integral.constant.Constant;
import com.integral.model.Result;
import com.integral.service.UserNameService;
import com.integral.service.base.SupplierService;
import com.integral.util.MD5;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.integral.model.IntegralSupplier;
import org.springframework.stereotype.Service;
import com.integral.model.RegisterEnttity;

import java.util.Date;

@Service
@Slf4j
public class UserNameServiceImpl implements UserNameService {

    @Autowired
    private SupplierService supplierService;


    /**
     * 密码验证
     *
     * @param userName
     * @param pwd
     * @return
     */
    @Override
    public Result checkPwd(String userName, String pwd) {

        boolean falg = checkPwdUtil(userName, pwd);
        log.info("密码校验结果{}", falg);
        if (falg) {
            log.info("登录成功");
            return Result.ok(Result.build(), "", "");
        }
        return Result.error(Result.build(), "账号或者密码错误", "");
    }

    /**
     * 更改密码
     *
     * @param userName
     * @param newPwd
     * @param oldPwd
     * @return
     */
    @Override
    public Result updatePwd(String userName, String newPwd, String oldPwd) {

        boolean flag = checkPwdUtil(userName, oldPwd);
        log.info("密码校验结果{}", flag);
        if (flag) {
            String encryptionPWD = MD5.md5(newPwd);
            supplierService.updatePwd(userName, encryptionPWD);
            return Result.ok(Result.build(), "", "");
        }
        return Result.error(Result.build(), "账号或者密码错误", "");

    }

    /**
     * 注册
     *
     * @param registerEnttity
     * @return
     */
    @Override
    public Result register(RegisterEnttity registerEnttity) {
        IntegralSupplier supplier = supplierService.queryByUserName(registerEnttity.getUser_name());
        if (registerEnttity != null && supplier==null) {
            supplier = new IntegralSupplier();
            BeanUtils.copyProperties(supplier,registerEnttity);
//            supplier.setUser_name(registerEnttity.getUser_name());
//            supplier.setPwd(registerEnttity.getPwd());
//            supplier.setPhone(registerEnttity.getPhone());
//            supplier.setPerson_liable(registerEnttity.getPerson_liable());
//            supplier.setAddress(registerEnttity.getAddress());
//            supplier.setEmail(registerEnttity.getEmail());
            supplier.setPoints_count("0");
            supplier.setCreate_date(new Date());
            supplier.setStatus(Constant.ENABLE);
            log.info(supplier.toString());
            supplierService.inserSupplier(supplier);
            return Result.ok(Result.build(), "", "");
        } else {
            return Result.error(Result.build(), "该账号已经存在", "");
        }
    }

    /**
     * 获取账号
     *
     * @param userName
     * @return
     */
    @Override
    public Result getUserName(String userName) {

        return Result.ok(Result.build(), "", supplierService.queryByUserName(userName).getId());
    }

    /**
     * 密码校验
     *
     * @param userName
     * @param pwd
     * @return
     */
    public boolean checkPwdUtil(String userName, String pwd) {
        if (StringUtils.isNotBlank(pwd)) {
            IntegralSupplier integralSupplier = supplierService.queryByUserName(userName);
            if (integralSupplier != null) {
                if (pwd.equals(integralSupplier.getPwd())) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
}
