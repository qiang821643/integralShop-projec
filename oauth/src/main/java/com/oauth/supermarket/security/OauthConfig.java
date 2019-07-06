package com.oauth.supermarket.security;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.security
 * @date:2019/6/27
 **/

@Configuration
//开启认证服务注解
@EnableAuthorizationServer
@Slf4j
public class OauthConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private SupplierDetails supplierDetails;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private DataSource dataSource;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Bean
    public TokenStore tokenStore(){
        //使用内存中的token

        return new InMemoryTokenStore();
        //数据库中的token
        //return new JdbcTokenStore();
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()").allowFormAuthenticationForClients();
    }

    /**
     * 密码模式
     * post请求
     * http://localhost:8091/oauth/token?grant_type=password&username=a&password=123&client_id=client&client_secret=123&scope=read
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        String secret = passwordEncoder.encode("123");
        log.info("secret--------->"+secret);
        clients.inMemory().withClient("client") //客户端ID
                .secret(secret) // base64 的编码结果
                .scopes("read")   //允许授权范围
                .authorizedGrantTypes("password","refresh_token") //设置验证方式  授权类型
                .accessTokenValiditySeconds(100000) //token过期时间
                .refreshTokenValiditySeconds(100000); //refresh_token过期时间
//        clients.inMemory().withClient("client")
//                .authorizedGrantTypes("authorization_code","client_credentials","password","refresh_token")
//                .scopes("test")
//                .secret(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("123"));
//        clients
//                .inMemory()
//                .withClient("clientapp").secret(passwordEncoder.encode("123456"))
//                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
//                .authorities("READ_ONLY_CLIENT")
//                .scopes("read_profile_info")
//                .resourceIds("oauth2-resource")
//                .redirectUris("http://localhost:8091/login")
//                .accessTokenValiditySeconds(120)
//                .refreshTokenValiditySeconds(240000);
    }


//     密码模式下配置认证管理器 AuthenticationManager,并且设置 AccessToken的存储介质tokenStore,如果不设置，则会默认使用内存当做存储介质
//      @param endpoints
//     @throws Exception

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints.tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .userDetailsService(supplierDetails);
    }
}
