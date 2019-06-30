package com.oauth.supermarket.security;

import com.oauth.supermarket.constant.Constant;
import com.oauth.supermarket.mapper.IntegralSupplierMapper;
import com.oauth.supermarket.model.IntegralSupplier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.security
 * @date:2019/5/29
 **/
@Component
@Slf4j
public class SupplierDetails implements UserDetailsService {

    @Autowired
    private IntegralSupplierMapper supplierMapper;

    /**
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        IntegralSupplier supplier = new IntegralSupplier();
        supplier.setUser_name(username);
        supplier.setStatus(Constant.ENABLE);

        IntegralSupplier supplier1 = supplierMapper.findOne(supplier);
        SelfUserDetails userInfo = new SelfUserDetails();
        userInfo.setUsername(username);
        String pwd = new BCryptPasswordEncoder().encode(supplier1.getPwd());
        userInfo.setId(supplier1.getId());
        userInfo.setPassword(pwd);

        Set authoritiesSet = new HashSet();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
        authoritiesSet.add(authority);
        userInfo.setAuthorities(authoritiesSet);

        return userInfo;

    }
}
