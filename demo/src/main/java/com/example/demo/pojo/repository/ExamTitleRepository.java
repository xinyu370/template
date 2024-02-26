package com.example.demo.pojo.repository;

import com.example.demo.pojo.ExamTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamTitleRepository extends JpaRepository<ExamTitle,Long> {
}
