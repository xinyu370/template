package com.example.demo.repository;

import com.example.demo.po.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities,Long> {
    List<Authorities> findByUserId(Long userId);
}
