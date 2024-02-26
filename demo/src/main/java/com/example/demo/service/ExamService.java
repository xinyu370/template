package com.example.demo.service;

import com.example.demo.common.pub.PageResult;
import com.example.demo.common.vo.ExamVo;
import com.example.demo.pojo.ExamTitle;

public interface ExamService {

    void addExam(ExamVo examVo);

    void deleteExam(Long id);

    void updateExam(ExamVo examVo);

    ExamVo getExamById(Long id);

    PageResult<ExamTitle> getExamPageList(ExamVo examVo);
}
