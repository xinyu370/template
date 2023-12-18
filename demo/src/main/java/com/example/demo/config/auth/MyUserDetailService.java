package com.example.demo.config.auth;

import com.example.demo.po.MyUser;
import com.example.demo.repository.MyUserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Resource
    private MyUserRepository myUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = myUserRepository.findByUsername(username);
        UserDetails userDetails = new MyUserDetails();
        if(user!=null){
             BeanUtils.copyProperties(user,userDetails);
             return userDetails;
        }
        throw new UsernameNotFoundException(username +"not found!");
    }
}
