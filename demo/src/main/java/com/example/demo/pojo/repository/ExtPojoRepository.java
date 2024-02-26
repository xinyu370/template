package com.example.demo.pojo.repository;

import com.example.demo.pojo.ExtPojo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtPojoRepository extends JpaRepository<ExtPojo,Long> {
}
