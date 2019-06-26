package com.security;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
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
public class AjaxAccessDeniedHandler implements AccessDeniedHandler {

    /**
     * 用户已经登陆,但是访问其自身没有权限的资源
     * @param request
     * @param response
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {

        JSONObject res = new JSONObject();
        res.put("success",false);
        res.put("msg","Need Authorities!");
        res.put("code","300");
        response.setStatus(300);
        response.setContentType("application/json;charset=UTF-8");

        response.getWriter().write(JSON.toJSONString(res));
    }
}
