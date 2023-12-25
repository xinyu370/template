package com.example.demo.po;

import com.example.demo.common.PasswordEncoderType;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String phoneNumber;
    private String tenantCode;
    private String enabled;
    private String delFlag;

    @Enumerated(EnumType.STRING)
    private PasswordEncoderType passwordEncoderType;

//    @OneToMany(mappedBy = "my_user", fetch = FetchType.EAGER)
//    private List<Authorities> authorities;
}
