package com.example.demo.pojo;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String phoneNumber;
    //0代表用户，1代表管理员
    private String tenantCode;
    //0禁用，1启用
    private String enabled;
    private String delFlag;
    //0代表男生 1，代表女生
    private String sex;

//    @Enumerated(EnumType.STRING)
//    private PasswordEncoderType passwordEncoderType;

//    @OneToMany(mappedBy = "my_user", fetch = FetchType.EAGER)
//    private List<Authorities> authorities;
}
