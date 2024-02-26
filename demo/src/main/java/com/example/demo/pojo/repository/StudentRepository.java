package com.example.demo.pojo.repository;

import com.example.demo.pojo.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository  extends JpaRepository<Student,Long> {
}
