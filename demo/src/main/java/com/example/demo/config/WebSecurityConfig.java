package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

//@Component
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    //用户信息
    private UserDetails userDetails = User.withUsername("HongLei")
            .password("xhl123").authorities("read","write").accountExpired(false)
            .disabled(true).build();

    //完成用户认证的实现
    private UserDetailsService userDetailsService;

    /**
     * 该类维护了一个 UserDetails 列表，并提供了一组 withUser 方法完成用户信息的初始化
     */
    private UserDetailsManagerConfigurer userDetailsManagerConfigurer;
    /**
     * 认证，认证的是请求本身，而具体的认证过程由组件来负责AuthenticationProvider
     * AuthenticationProvider流程：先去UserDetailsService加载用户，获取到了就去判断密码
     */
    private Authentication authentication;
    private AuthenticationProvider authenticationProvider;

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 主要用作认证方式的一些改变，例如：登录页面配置，拦截接口配置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }

    /**
     * 认证过程设计用户信息交互，可以重写该方法
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("user_1").password("user_1").roles("user")
                .and().withUser("admin_1").password("admin_1").roles("admin","user");
//        auth.jdbcAuthentication().dataSource(dataSource)
//        .usersByUsernameQuery("select username,password,enabled from users where username=?")
//        .authoritiesByUsernameQuery("select username, authority from UserAuthorities " + "where username=?")
//        .passwordEncoder(new BCryptPasswordEncoder());
    }


}
