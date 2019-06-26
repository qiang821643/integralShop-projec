package com.security;


import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.security
 * @date:2019/6/3
 **/

@Component
public class MyAuthencationEntryPoint implements AuthenticationEntryPoint {

    /**
     * 引导用户登陆
     * @param request
     * @param response
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException, ServletException {
        JSONObject res = new JSONObject();
        res.put("success",true);
        res.put("msg","当前" +
                "用户未登陆，请先登陆在访问");
        res.put("status","500");
        response.setStatus(500);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().append(res.toString());
    }
}
