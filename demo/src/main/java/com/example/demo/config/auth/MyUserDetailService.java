package com.example.demo.config.auth;

import com.example.demo.pojo.Authorities;
import com.example.demo.pojo.MyUser;
import com.example.demo.pojo.repository.AuthoritiesRepository;
import com.example.demo.pojo.repository.MyUserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.function.Supplier;

@Component
public class MyUserDetailService implements UserDetailsService {
    @Resource
    private MyUserRepository myUserRepository;
    @Autowired
    private AuthoritiesRepository authoritiesRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Supplier<UsernameNotFoundException> s = ()->new UsernameNotFoundException("username:"+username+"in invalid!");
        MyUser user = myUserRepository.findByUsername(username).orElseThrow(s);
        List<Authorities> authorities = authoritiesRepository.findByUserId(user.getId());
        MyUserDetails userDetails = new MyUserDetails();
        BeanUtils.copyProperties(user,userDetails);
        userDetails.setAuthorities(authorities);
        return  userDetails;
    }
}
