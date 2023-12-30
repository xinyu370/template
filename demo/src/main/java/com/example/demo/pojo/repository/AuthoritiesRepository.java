package com.example.demo.pojo.repository;

import com.example.demo.pojo.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities,Long> {
    List<Authorities> findByUserId(Long userId);
}
