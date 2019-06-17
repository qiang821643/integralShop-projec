package com.integral.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.integral.mapper.IntegralMemberCcsMapper;
import com.integral.model.IntegralMemberCcs;
import com.integral.security.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import io.jsonwebtoken.Claims;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 拦截器校验token
 */

@Component
@Slf4j
public class PhoneInterceptor implements HandlerInterceptor {

    @Autowired
    private IntegralMemberCcsMapper memberCcsMapper;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        String authorization = request.getHeader("Authorization");
        //String hender = request.getQueryString();

        if(StringUtils.isNotBlank(authorization) && authorization.startsWith("Bearerey:")){
            log.info(authorization);
            final String token = authorization.substring("Bearerey:".length());
            log.info(token);
            Claims claims = JwtTokenUtil.parseToken(token);
            IntegralMemberCcs param = new IntegralMemberCcs();
            param.setId(Integer.valueOf(claims.getId()));
            IntegralMemberCcs memberCcs = memberCcsMapper.findOne(param);
            if(memberCcs!=null){
                request.setAttribute("integralMemberCcs",memberCcs);
                return true;
            }
        }

        result(response);
        return false;
    }

    public void result(ServletResponse response){
        try {
            JSONObject result = new JSONObject();
            result.put("success",false);
            result.put("msg","Need Authorities!");
            result.put("code","500");
            response.getWriter().write(JSONObject.toJSONString(result));
        }catch (IOException e){
            log.error(e.toString());
            e.printStackTrace();
        }


    }
}
