package com.integral.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.google.common.collect.Lists;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import com.integral.interceptor.PhoneInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * 解决跨域问题
 *
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

    /**
     * 德鲁伊servlet
     * @return
     * http://localhost:8088/druid2/index.html
     */
    @Bean
    public ServletRegistrationBean druidServletRegist(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid2/*");
        servletRegistrationBean.addInitParameter("allow","127.0.0.1");

        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.

        servletRegistrationBean.addInitParameter("deny","192.168.1.73");

        //登录查看信息的账号密码.

        servletRegistrationBean.addInitParameter("loginUsername","admin2");

        servletRegistrationBean.addInitParameter("loginPassword","123456");

        //是否能够重置数据.

        servletRegistrationBean.addInitParameter("resetEnable","false");
        return servletRegistrationBean;

    }

    /**
     *德鲁伊过滤器
     */
    @Bean
    public void druidFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid2/*");
        registrationBean.addUrlPatterns("/*");
    }

    @Bean
    @Primary
    public DataSource druidDataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername("kaifa");
        dataSource.setPassword("zsl.com");
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@localhost:ORCL");
        dataSource.setInitialSize(10);
        dataSource.setMinIdle(5);
        dataSource.setMaxActive(20);
        dataSource.setMaxWait(6000);
        /*配置间隔多久检查1次,检测需要关闭的空闲连接，单位是毫秒*/
        dataSource.setTimeBetweenEvictionRunsMillis(6000);
        /*连接在池中最小生存的时间，单位是毫秒*/
        dataSource.setMinEvictableIdleTimeMillis(3000);
        /*打开PSCache，并且指定每个连接上PSCache的大小*/
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        /*通过connectProperties属性来打开mergeSql功能*/
        //dataSource.setConnectProperties("=druid.stat.mergeSql=true");

        dataSource.setValidationQuery("SELECT * FROM DUAL");

        dataSource.setFilters("stat,wall,slf4j");
        dataSource.init();
        return dataSource;

    }
}
