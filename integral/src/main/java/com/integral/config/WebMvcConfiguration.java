package com.integral.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.google.common.collect.Lists;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
import java.util.Properties;

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


    private final List<String> url = Lists.newArrayList("/goods/shelves");

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
     *
     * @return http://localhost:8088/druid2/index.html
     */
    @Bean
    public ServletRegistrationBean druidServletRegist() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        servletRegistrationBean.addInitParameter("allow", "");

        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.

        servletRegistrationBean.addInitParameter("deny", "192.168.1.73");

        //登录查看信息的账号密码.

        servletRegistrationBean.addInitParameter("loginUsername", "a");

        servletRegistrationBean.addInitParameter("loginPassword", "123");

        //是否能够重置数据.

        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;

    }

    /**
     * 德鲁伊过滤器
     */
    @Bean
    public void druidFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid2/*");
        registrationBean.addUrlPatterns("/*");
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() throws SQLException {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setUsername(this.userName);
//        dataSource.setPassword(this.passWord);
//        dataSource.setDriverClassName(this.driver);
//        dataSource.setUrl(this.oracleUrl);
//        dataSource.setInitialSize(this.initialSize);
//        dataSource.setMinIdle(this.minIdle);
//        dataSource.setMaxActive(this.maxActive);
//        dataSource.setMaxWait(this.maxWait);
//        /*配置间隔多久检查1次,检测需要关闭的空闲连接，单位是毫秒*/
//        dataSource.setTimeBetweenEvictionRunsMillis(this.timeBetweenEvictionRunsMillis);
//        /*连接在池中最小生存的时间，单位是毫秒*/
//        dataSource.setMinEvictableIdleTimeMillis(this.minEvictableIdleTimeMillis);
//        /*打开PSCache，并且指定每个连接上PSCache的大小*/
//        dataSource.setPoolPreparedStatements(this.poolPreparedStatements);
//        dataSource.setMaxPoolPreparedStatementPerConnectionSize(this.maxPoolPreparedStatementPerConnectionSize);
//        //dataSource.setAsyncCloseConnectionEnable(this.asyncCloseConnectionEnable);
//        /*通过connectProperties属性来打开mergeSql功能*/
//        //dataSource.setConnectProperties("=druid.stat.mergeSql=true");
//        dataSource.setTestOnBorrow(this.testOnBorrow);
//        dataSource.setTestOnReturn(this.TestOnReturn);
//        dataSource.setTestWhileIdle(this.testWhileIdle);
//        dataSource.setValidationQuery(this.validationQuery);
//        //dataSource.setValidationQueryTimeout(this.validationQueryTimeout);
//        dataSource.setConnectProperties(this.connectProperties);
//        dataSource.setFilters(this.filters);
//        dataSource.init();
        return new DruidDataSource();

    }
}
