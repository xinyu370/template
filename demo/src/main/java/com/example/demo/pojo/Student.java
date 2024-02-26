package com.example.demo.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long classId;
    private String stuName;
    private String stuPhone;
    private String stuArea;
    private String stuSpace;
    private String stuType;
    /**
     create table `student`(
        `id` bigint(10) not null auto_increment,
        `class_id` bigint(10) not null,
        `stu_name` varchar(64),
        `stu_phone` varchar(64),
        `stu_area` varchar(64),
        `stu_space` varchar(64),
        `stu_type` varchar(64),
        primary key(`id`)
     );
     */

}
