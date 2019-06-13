package com.integral.security;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
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
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private  SupplierDetails supplierDetails;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //String authention = request.getHeader("Authorization");

        String authention = request.getQueryString();
        log.info(authention);
        if(authention != null && authention.startsWith("Authorization=Bearerey:")){
            final String authToken = authention.substring("Authorization=Bearerey:".length());
            log.info("token:{}",authToken);
            Claims claims = JwtTokenUtil.parseToken(authToken);
            if(claims != null && SecurityContextHolder.getContext().getAuthentication()== null){
                UserDetails userDetails = supplierDetails.loadUserByUsername(claims.getSubject());
                if(userDetails!=null){
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request,response);
    }
}
