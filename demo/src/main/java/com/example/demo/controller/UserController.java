package com.example.demo.controller;

import com.example.demo.common.R;
import com.example.demo.common.pub.PageResult;
import com.example.demo.common.req.UserReqVo;
import com.example.demo.common.resp.UserRespVo;
import com.example.demo.config.MyPassWordEncoder;
import com.example.demo.pojo.Authorities;
import com.example.demo.pojo.MyUser;
import com.example.demo.pojo.repository.MyUserRepository;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@Api(tags = "用户接口")
public class UserController {

    @Autowired
    private MyUserRepository myUserRepository;
    @Autowired
    private MyPassWordEncoder myPassWordEncoder;

    @Autowired
    private UserService userService;


    @PostMapping("/updateUser")
    @ApiOperation(value = "更新用户")
    public R updateUser(@RequestBody MyUser myUser){
        userService.updateUser(myUser);
        return R.ok();
    }

    @PostMapping("/addUser")
    @ApiOperation(value = "add用户")
    public R addUser(@RequestBody MyUser myUser) throws Exception {
        userService.addUser(myUser);
        return R.ok();
    }

    @PostMapping("/getUserList")
    @ApiOperation(value = "query用户")
    public R<PageResult<UserRespVo>> getUserList(@RequestBody UserReqVo reqVo) throws Exception {
        return R.ok(userService.getAllUserList(reqVo));
    }

    @GetMapping("/permitUser")
    public R permitUser(@RequestParam(value = "id",required = true) Long id) throws Exception {
        userService.permitUser(id);
        return R.ok();
    }

    @DeleteMapping("/deleteUser")
    @ApiOperation(value = "delete用户")
    public R deleteUser(@RequestParam("userId") Long userId){
        userService.deleteUser(userId);
        return R.ok();
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
}
