package com.example.demo.service;

import com.example.demo.common.pub.PageResult;
import com.example.demo.common.vo.StudentVo;
import com.example.demo.pojo.Student;
import com.example.demo.pojo.StudyInfo;

import java.util.List;

public interface StuService {

    void addStu(Student student);

    void delStu(Long id);

    StudentVo getStudent(Long id);

    void updateStu(Student student);

    PageResult<Student> getStuList(StudentVo student);


    void addAndUpdateStudyInfo(StudyInfo studyInfo);

    List<StudyInfo> getStudyInfo(String stuName);
}
