package com.example.demo.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyPassWordEncoder implements PasswordEncoder {
    /**
     * 测试用，进行明文匹配
     * @param charSequence
     * @return
     */
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}
