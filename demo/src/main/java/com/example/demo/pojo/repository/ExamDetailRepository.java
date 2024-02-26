package com.example.demo.pojo.repository;

import com.example.demo.pojo.ExamDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamDetailRepository extends JpaRepository<ExamDetail,Long> {
    void deleteByExamId(Long id);
    void removeByExamId(Long examId);
    List<ExamDetail> getExamDetailByExamId(Long examId);
}
