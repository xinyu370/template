package com.example.demo.config.auth;

import com.alibaba.fastjson.JSON;
import com.example.demo.config.MyPassWordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class MyUserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private MyUserDetailService myUserDetailService;

    private PasswordEncoder passwordEncoder = new MyPassWordEncoder();

    //执行认证，返回认证结果
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String passWord = authentication.getCredentials().toString();
        MyUserDetails userDetails = (MyUserDetails)myUserDetailService.loadUserByUsername(userName);
        if(passwordEncoder.matches(passWord,userDetails.getPassword()) && "1".equals(userDetails.getEnabled())){
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, passWord, userDetails.getAuthorities());
            //存放authentication到SecurityContextHolder
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            return usernamePasswordAuthenticationToken;
        }else{
            throw new BadCredentialsException("用户名/密码错误或者用户未启用(请联系管理员启用)");
        }
    }

    //判断是否支持当前的认证对象
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
