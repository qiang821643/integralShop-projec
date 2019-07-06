package com.oauth.supermarket.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.authentication.AuthenticationManagerFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.security
 * @date:2019/5/18
 **/

@Configuration
@EnableWebSecurity
public class SecurityConfig extends  WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenctiationFailureHandler myAuthenctiationFailureHandler;

    @Autowired
    private MyAuthenctiationSuccessHandler myAuthenctiationSuccessHandler;

    @Autowired
    private MyAuthencationEntryPoint myAuthencationEntryPoint;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private AjaxAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private RbacAuthorityService rbacAuthorityService;

    @Autowired
    private SupplierDetails supplierDetails;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder encoder(){

        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // 加密
        auth.userDetailsService(supplierDetails).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


//
//           http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //使用JWT关闭token
//                    .and()
//                    .httpBasic().authenticationEntryPoint(myAuthencationEntryPoint)
//                    .and()
//                    .authorizeRequests()
//                    .antMatchers("/login","/goods/*","/member/*","/menu/*","/druid/*")
//                    .permitAll()
//                    //.anyRequest()
//                    //.authenticated()
//                    .and()
//                    .formLogin()
//                    //.loginPage("http://47.94.251.90/frontend-shop/dist/#/login")
//                    .successHandler(myAuthenctiationSuccessHandler)
//                    .failureHandler(myAuthenctiationFailureHandler)
//                    .permitAll()
//                   .and().authorizeRequests().anyRequest().authenticated();
//
//            http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
            //http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);




        //http.authorizeRequests().antMatchers("/").permitAll().and().csrf().disable();

    }


}
