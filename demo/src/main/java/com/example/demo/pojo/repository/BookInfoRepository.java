package com.example.demo.pojo.repository;

import com.example.demo.pojo.BookInfo;
import com.example.demo.pojo.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookInfoRepository extends JpaRepository<BookInfo,Long> {

    List<BookInfo> getBookInfoByAuthorAndAndBookNames(String author, String bookNames);
}
