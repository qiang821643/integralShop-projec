package com.oauth.supermarket.security;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component("MyAuthenctiationSuccessHandler")
public class MyAuthenctiationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.info("登录成功");
        JSONObject res = new JSONObject();
        res.put("success",true);
        res.put("msg","登录成功");
        res.put("code","200");
        response.setStatus(200);
        response.setContentType("application/json;charset=UTF-8");
        SelfUserDetails userDetails = (SelfUserDetails) authentication.getPrincipal();
        String jwtToken = jwtTokenUtil.generateToken(userDetails.getUsername(), "admin",userDetails.getId());
        res.put("jwtToke",jwtToken);
        response.getWriter().append(res.toString());
    }
}

