package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

//@Component
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService myUserDetailService;

    @Autowired
    private AuthenticationProvider authenticationProvider;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider).userDetailsService(myUserDetailService).passwordEncoder(new BCryptPasswordEncoder());
//        auth.inMemoryAuthentication().passwordEncoder(new MyPassWordEncoder()).withUser("xhl1").password("123").roles("user")
//                .and().withUser("xhl2").password("123").roles("admin");
    }
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //对请求进行权限验证
//        http.authorizeRequests().mvcMatchers("/hello_users").hasRole("user")
//                .mvcMatchers("/hello_admin").hasRole("admin").anyRequest().authenticated()
//                .and().formLogin().and().httpBasic();
        http.formLogin().defaultSuccessUrl("/healthrecord",true);
        http.authorizeRequests().anyRequest().authenticated();
    }
//    public static void main(String[] args) {
//        String valueToEncrept = "hello";
//        String salt = KeyGenerators.string().generateKey();
//        System.out.println("salt:"+salt);
//        BytesEncryptor standard = Encryptors.standard("password", salt);
//        final byte[] encrypt = standard.encrypt(valueToEncrept.getBytes(StandardCharsets.UTF_8));
//        final byte[] decrypt = standard.decrypt(encrypt);
//        System.out.println(encrypt);
//        System.out.println(decrypt);
//    }
}
