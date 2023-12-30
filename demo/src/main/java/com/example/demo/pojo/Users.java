package com.example.demo.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Accessors
public class Users {
    @Id
    private String username;

    private String password;

    private String enabled;
}
