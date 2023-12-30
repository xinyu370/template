package com.example.demo.service;

import com.example.demo.common.pub.PageResult;
import com.example.demo.common.req.BookReqVo;
import com.example.demo.common.req.ChapterInfo;
import com.example.demo.common.req.UserReadCountReq;
import com.example.demo.common.resp.BookRespVo;
import com.example.demo.common.resp.ReadBookRespVo;
import com.example.demo.common.resp.UserReadCountResp;

import java.awt.print.Book;
import java.io.IOException;

public interface BookService {
    void createBook(BookReqVo reqVo) throws IOException;

    void addChapter(ChapterInfo req) throws IOException;

    PageResult<BookRespVo> getBookList(BookReqVo reqVo);

    void updateBookStatus(long bookId,String status);

    ReadBookRespVo readBook(long bookId,Integer chapter) throws Exception;


    PageResult<UserReadCountResp> totalReadingInfo(UserReadCountReq req);
}
