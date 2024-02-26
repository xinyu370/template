package com.example.demo.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class ExtPojo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tag;

    private String ext1;
    private String ext2;
    private String  ext3;
    private String ext4;
    private String ext5;
    private String ext6;
    private String ext7;
    private String ext8;
    private String ext9;
    private String ext10;

}
