package com.example.demo.common.vo;

import com.example.demo.common.pub.PageVo;
import lombok.Data;

@Data
public class StudentVo extends PageVo {
    private Long id;
    private Long classId;
    private String stuName;
    private String stuPhone;
    private String stuArea;
    private String stuSpace;
    private String stuType;
    private String className;
}
