package com.integral.config;


import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import com.integral.interceptor.PhoneInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import java.util.List;
/**
 * 解决跨域问题
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.config
 * @date:2019/6/3
 **/
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private PhoneInterceptor phoneInterceptor;

    private final List<String> url = Lists.newArrayList("/goods/*");

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        /*是否允许请求带有验证信息*/
        corsConfiguration.setAllowCredentials(true);
        /*允许访问的客户端域名*/
        corsConfiguration.addAllowedOrigin("*");
        /*允许服务端访问的客户端请求头*/
        corsConfiguration.addAllowedHeader("*");
        /*允许访问的方法名,GET POST等*/
        corsConfiguration.addAllowedMethod("*");
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(phoneInterceptor).addPathPatterns(url);
    }

}
