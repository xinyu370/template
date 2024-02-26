package com.example.demo.service.impl;

import com.example.demo.common.BusinessException;
import com.example.demo.common.pub.PageResult;
import com.example.demo.common.vo.StudentVo;
import com.example.demo.pojo.ExtPojo;
import com.example.demo.pojo.Student;
import com.example.demo.pojo.StudyInfo;
import com.example.demo.pojo.repository.StudentRepository;
import com.example.demo.pojo.repository.StudyInfoRepository;
import com.example.demo.service.ExtService1;
import com.example.demo.service.StuService;
import com.example.demo.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StuServiceImpl implements StuService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ExtService1 extService1;
    @Autowired
    private StudyInfoRepository studyInfoRepository;

    @Override
    public void addStu(Student student) {
        if(student.getClassId()==null){
            throw new BusinessException("请选择班级");
        }
        studentRepository.save(student);
    }

    @Override
    public void delStu(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public StudentVo getStudent(Long id) {
        StudentVo resp = new StudentVo();
        Student student = studentRepository.getById(id);
        BeanUtils.copyProperties(student,resp);
        if(student!=null){
            ExtPojo classInfo = extService1.getExtById(student.getClassId());
            resp.setClassName(classInfo==null?"":classInfo.getExt1());
        }
        return resp;
    }

    @Override
    public void updateStu(Student student) {
        if(student.getId()==null){
            throw new BusinessException("更新id不能为空");
        }
        if(student.getClassId()==null){
            throw new BusinessException("请选择课程");
        }
        studentRepository.save(student);
    }

    @Override
    public PageResult<Student> getStuList(StudentVo student) {
        PageRequest page = PageRequest.of(student.getPageNum() - 1, student.getPageSize());
        Page<Student> all = studentRepository.findAll(page);
        return PageResult.<Student>builder().data(all.getContent())
                .count(all.getTotalElements()).build();
    }

    @Override
    public void addAndUpdateStudyInfo(StudyInfo studyInfo) {
        if(StringUtils.isEmpty(studyInfo.getStuName()) || StringUtils.isEmpty(studyInfo.getCourseName())){
            throw new BusinessException("学生姓名/课程不能为空");
        }
        StudyInfo byStuNameAndCourseName = studyInfoRepository.findByStuNameAndCourseName(studyInfo.getStuName(), studyInfo.getCourseName());
        if(byStuNameAndCourseName != null){
            BeanUtils.copyProperties(byStuNameAndCourseName,studyInfo);
            studyInfo.setId(byStuNameAndCourseName.getId());
        }
        studyInfoRepository.save(studyInfo);
    }

    public List<StudyInfo> getStudyInfo(String stuName){
        return studyInfoRepository.findByStuName(stuName);
    }
}
