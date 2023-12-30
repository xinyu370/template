package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class UserCurrentUtils {


    public static final String USER = "user";
    public static final String UN_LOGIN = "anonymousUser";

    public synchronized static String init() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (UN_LOGIN.equals(principal)) {
            throw new Exception("用户未登录");
        }
        return (String) principal;
    }

    public static String getCurrentUserName() throws Exception {
        return init();
    }

}
