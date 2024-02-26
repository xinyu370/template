package com.example.demo.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableAuthorizationServer
public class SpringAuthorizationServerConfigure extends AuthorizationServerConfigurerAdapter {

    /**
     * 认证管理器
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;
    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private TokenEndpoint tokenEndpoint;
    /**
     * @Description:对@RequestMapping(value = "/oauth/token", method=RequestMethod.GET)的方法进行get方法支持
     * @author:hutao
     * @mail:hutao_2017@aliyun.com
     * @date:2021年4月23日
     */
    @PostConstruct
    public void reconfigure() {
        Set<HttpMethod> allowedMethods =new HashSet<>(Arrays.asList(HttpMethod.GET, HttpMethod.POST));
        tokenEndpoint.setAllowedRequestMethods(allowedMethods);
    }

//    private JwtTokenEnhancer tokenEnhancer;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtAccessTokenConverter));
        endpoints.tokenStore(tokenStore).accessTokenConverter(jwtAccessTokenConverter)
                .tokenEnhancer(tokenEnhancerChain)
                .authenticationManager(authenticationManager).userDetailsService(userDetailsService);
    }


    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("123456");
        return converter;
    }

    @Bean
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }
    /**
     * 可以配置授权模式
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("spring")
                .secret("{noop}spring_secret")
                .authorizedGrantTypes("refresh_token", "password", "client_credentials")
                .scopes("webclient", "mobileclient");
    }
}
