package com.example.demo.service;

import com.example.demo.common.pub.PageResult;
import com.example.demo.common.req.UserReadCountReq;
import com.example.demo.common.req.UserReqVo;
import com.example.demo.common.resp.UserReadCountResp;
import com.example.demo.common.resp.UserRespVo;
import com.example.demo.pojo.Authorities;
import com.example.demo.pojo.MyUser;

import java.util.List;

public interface UserService {

    void addUser(MyUser myUser) throws Exception;

    PageResult<UserRespVo> getAllUserList (UserReqVo reqVo) throws Exception;

    void permitUser(Long userId) throws Exception;

    boolean isAdmin() throws Exception;

    void deleteUser(Long id);

    void auth(MyUser user);

    boolean check(Authorities authorities);

}
