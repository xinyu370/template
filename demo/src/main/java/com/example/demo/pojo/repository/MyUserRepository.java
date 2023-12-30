package com.example.demo.pojo.repository;

import com.example.demo.pojo.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser,Long> {
    Optional<MyUser> findByUsername(String userName);
}
