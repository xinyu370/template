package com.example.demo.common.vo;

import com.example.demo.common.pub.PageVo;
import com.example.demo.pojo.ExamDetail;
import lombok.Data;

import java.util.List;

@Data
public class  ExamVo  extends PageVo {
    private Long id;

    private String examName;

    private String createCer;

    private String examStartTime;

    private String examEndTime;
    List<ExamDetail> questionList;
}
