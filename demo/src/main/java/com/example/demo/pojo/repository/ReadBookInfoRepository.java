package com.example.demo.pojo.repository;

import com.example.demo.pojo.ReadBookInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReadBookInfoRepository extends JpaRepository<ReadBookInfo, Long> {

    Optional<ReadBookInfo> findByBookIdAndUserName(Long bookId,String userName);
}
