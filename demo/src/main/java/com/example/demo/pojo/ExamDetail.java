package com.example.demo.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class ExamDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long examId;

    private String questionDesc;
    private Integer sort;
    private String ans_a;
    private String ans_b;
    private String ans_c;
    private String ans_d;
    private String ans;

}
