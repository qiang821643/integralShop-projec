package com.oauth.supermarket.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
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

//开启认证服务注解
@EnableAuthorizationServer
public class OauthConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private SupplierDetails supplierDetails;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private DataSource dataSource;


    @Bean
    public TokenStore tokenStore(){
        //使用内存中的token

        return new InMemoryTokenStore();
        //数据库中的token
        //return new JdbcTokenStore();
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("permitAll()").allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.inMemory().withClient("integrall") //客户端ID
                .secret("") // base64 的编码结果
                .scopes("read","write")   //允许授权范围
                .authorizedGrantTypes("password","refresh_token") //设置验证方式  授权类型
                .accessTokenValiditySeconds(10000) //token过期时间
                .refreshTokenValiditySeconds(10000); //refresh_token过期时间

    }

    /**
     *密码模式下配置认证管理器 AuthenticationManager,并且设置 AccessToken的存储介质tokenStore,如果不设置，则会默认使用内存当做存储介质
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager).userDetailsService(supplierDetails);
    }
}
