package com.example.demo.repository;

import com.example.demo.po.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    List<UserEntity> findAllByClassfyId(String classfyId);
}
