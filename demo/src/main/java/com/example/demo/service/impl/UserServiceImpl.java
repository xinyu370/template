package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.BusinessException;
import com.example.demo.common.pub.PageResult;
import com.example.demo.common.req.UserReqVo;
import com.example.demo.common.resp.UserRespVo;
import com.example.demo.config.MyPassWordEncoder;
import com.example.demo.pojo.Authorities;
import com.example.demo.pojo.MyUser;
import com.example.demo.pojo.mapper.MyUserMapper;
import com.example.demo.pojo.repository.MyUserRepository;
import com.example.demo.service.UserService;
import com.example.demo.utils.PageUtil;
import com.example.demo.utils.UserCurrentUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.function.Supplier;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private MyUserRepository myUserRepository;
    @Autowired
    private MyPassWordEncoder myPassWordEncoder;
    @Autowired
    private MyUserMapper userMapper;
    @Override
    public void addUser(MyUser myUser) throws Exception {
        checkUser(myUser);
        if(myUserRepository.findByUsername(myUser.getUsername()).isPresent()){
            throw new BusinessException("当前用户名已被使用:"+myUser.getUsername());
        }
        myUser.setEnabled("0");
        myUser.setPassword(myPassWordEncoder.encode(myUser.getPassword()));
        myUserRepository.save(myUser);
    }

    public void updateUser(MyUser user){
        if(user.getId() == null){
            throw new BusinessException("更新用户，id不能为空");
        }
        myUserRepository.save(user);
    }

    @Override
    public PageResult<UserRespVo> getAllUserList(UserReqVo reqVo) throws Exception {
        Page<UserRespVo> page = PageUtil.buildPage(reqVo.getPageNum(), reqVo.getPageSize());
        List<UserRespVo> userList = userMapper.getUserList(page,reqVo);
        return PageResult.<UserRespVo>builder()
                .data(userList)
                .count(page.getTotal())
                .build();
    }

    @Override
    @Transactional
    public void permitUser(Long userId) throws Exception {
        if(!isAdmin()){
            throw new BusinessException("审核功能仅管理员能使用");
        }
        Supplier<Exception>  ex = ()->new BusinessException("根据id未找到用户");
        MyUser curUser = myUserRepository.findById(userId).orElseThrow(ex);
        curUser.setEnabled("1");
        myUserRepository.saveAndFlush(curUser);
    }

    @Override
    public boolean isAdmin() throws Exception {
        Supplier<Exception>  ex = ()->new BusinessException("未获取当前登录用户");
        String curUserName = UserCurrentUtils.getCurrentUserName();
        log.info("current user ==>{}", curUserName);
        MyUser curUser = myUserRepository.findByUsername(curUserName).orElseThrow(ex);
        if("admin".equals(curUser.getTenantCode())){
            return true;
        }
        return false;
    }

    @Override
    public void deleteUser(Long id) {
        myUserRepository.deleteById(id);
    }

    private void checkUser(MyUser myUser) throws Exception {
        if(StringUtils.isEmpty(myUser.getUsername())){
            throw new BusinessException("用户名不能为空！");
        }
        if(StringUtils.isEmpty(myUser.getPassword())){
            throw new BusinessException("密码不能为空！");
        }
        if(StringUtils.isEmpty(myUser.getTenantCode())){
            myUser.setTenantCode("user");
        }
    }

    @Override
    public void auth(MyUser user) {

    }

    @Override
    public boolean check(Authorities authorities) {
        return false;
    }

//    private void generateOrRenewAutoCode (MyUser u) {
//        String generatedCode = GenerateCodeUtil.generateCode();
//        Optional<AuthCode> autoCode = autoCodeRepository.findAuthCodeByUsername(u.getUsername());
//        if (autoCode.isPresent()) {//如果存在认证码，则刷新该认证码
//            AuthCode code = autoCode.get();
//            code.setCode(generatedCode);
//        } else {//如果没有找到认证码，则生成并保存一个新的认证码
//            AuthCode code = new AuthCode();
//            code.setUsername(u.getUsername());
//            code.setCode(generatedCode);
//            autoCodeRepository.save(code);
//        }
//    }
}
