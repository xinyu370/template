package com.example.demo.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class StudyInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stuName;

    private Long courseId;

    private String courseName;

    private String studyPercent;
    /**
     create table `study_info`(
     `id` bigint(10) not null auto_increment,
     `stu_name` varchar(64),
     `course_id` bigint(10),
     `course_name` varchar(64),
     `study_percent` varchar(64),
     primary key(`id`)
     );
     */

}
