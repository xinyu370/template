package com.example.demo.service.impl;

import com.example.demo.po.Authorities;
import com.example.demo.po.MyUser;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
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
