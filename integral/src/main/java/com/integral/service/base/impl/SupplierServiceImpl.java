package com.integral.service.base.impl;

import com.integral.mapper.IntegralSupplierMapper;
import com.integral.model.IntegralSupplier;
import com.integral.service.base.SupplierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private IntegralSupplierMapper integralSupplierMapper;

    /**
     * 通过账号查询供应商
     *
     * @param userName
     * @return
     */
    @Override
    public IntegralSupplier queryByUserName(String userName) {

        return integralSupplierMapper.queryByUserName(userName);
    }

    /**
     * 更改密码
     *
     * @param userName
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updatePwd(String userName,String pwd) {

        try {
            integralSupplierMapper.updatePwdByUserName(userName,pwd);

        }catch (Exception e){
            throw new RuntimeException("修改密码服务异常");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void inserSupplier(IntegralSupplier integralSupplier) {
        try {
            integralSupplierMapper.insertSuppiler(integralSupplier);
        }catch (Exception e){
            log.error("com.integral.service.base.impl.SupplierServiceImpl.inserSupplier{}",e.getMessage());
            throw new RuntimeException("注册服务异常");
        }

    }


}
