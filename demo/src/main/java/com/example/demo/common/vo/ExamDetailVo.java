package com.example.demo.common.vo;

import lombok.Data;

@Data
public class ExamDetailVo {
    private String questionDesc;
    private Integer sort;
    private String ans_a;
    private String ans_b;
    private String ans_c;
    private String ans_d;
    private String ans;

}
