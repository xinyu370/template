package com.example.demo.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


//@Component
public class MyUserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private MyUserDetailService myUserDetailService;
    @Autowired
    private PasswordEncoder passwordEncoder ;

    //执行认证，返回认证结果
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String passWord = authentication.getCredentials().toString();
        UserDetails userDetails = myUserDetailService.loadUserByUsername(userName);
        if(passwordEncoder.matches(passWord,userDetails.getPassword())){
            return new UsernamePasswordAuthenticationToken(userName,passWord, userDetails.getAuthorities());
        }else{
            throw new BadCredentialsException("The username or password is wrong!");
        }
    }

    //判断是否支持当前的认证对象
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
