package com.example.demo.pojo.repository;

import com.example.demo.pojo.StudyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyInfoRepository extends JpaRepository<StudyInfo,Long> {

    List<StudyInfo> findByStuName(String stuName);

    StudyInfo findByStuNameAndCourseName(String stuName,String courseName);
}
