package com.example.demo.controller;

import com.example.demo.po.Authorities;
import com.example.demo.po.MyUser;
import com.example.demo.repository.MyUserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class DemoController {

    @Autowired
    private MyUserRepository myUserRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @PostMapping("/addUser2")
    public String addUser2(@RequestBody MyUser myUser){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        myUser.setPassword(passwordEncoder.encode(myUser.getPassword()));
        myUserRepository.save(myUser);
        System.out.println("run finish！");
        return "finish";
    }

    //通过用户名和密码对用户进行首次认证
    @PostMapping("/user/auth")
    public String auth(@RequestBody MyUser myUser){
        userService.auth(myUser);
        return "success";
    }

    //通过用户名+验证码进行第二次验证
    @PostMapping("/authCode/check")
    public String check(@RequestBody Authorities authorities, HttpServletResponse response){
        if (userService.check(authorities)) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
        return "success";
    }

    @GetMapping("/hello_user")
    public String helloUser() {
        return "Hello User!";
    }

    @GetMapping("/hello_admin")
    public String helloAdmin() {
        return "Hello Admin!";
    }

    @GetMapping("/other")
    public String other() {
        return "Other!";
    }
}
