package com.example.demo.service;

import com.example.demo.po.Authorities;
import com.example.demo.po.MyUser;

public interface UserService {

    void auth(MyUser user);

    boolean check(Authorities authorities);
}
