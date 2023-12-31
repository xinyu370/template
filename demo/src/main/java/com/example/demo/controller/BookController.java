package com.example.demo.controller;

import com.example.demo.common.R;
import com.example.demo.common.pub.PageResult;
import com.example.demo.common.req.BookReqVo;
import com.example.demo.common.req.ChapterInfo;
import com.example.demo.common.req.UserReadCountReq;
import com.example.demo.common.resp.BookRespVo;
import com.example.demo.common.resp.ReadBookRespVo;
import com.example.demo.common.resp.UserReadCountResp;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * 查询书籍列表
     */
    @PostMapping("/getBookLists")
    public R<PageResult<BookRespVo>> getBookLists(@RequestBody BookReqVo reqVo){
       return R.ok(bookService.getBookList(reqVo));
    }
    /**
     * 新建一本书？放在本地以txt形式，数据库记录位置，who made it?
     */
    @PostMapping("/createBook")
    public R createBook(@RequestBody BookReqVo reqVo) throws IOException {
        bookService.createBook(reqVo);
        return R.ok();
    }

    /**
     * 添加章节，在写
     */
    @PostMapping("/addChapter")
    public R addChapter(@RequestBody ChapterInfo req) throws IOException {
        bookService.addChapter(req);
        return R.ok();
    }

    /**
     * 更新状态(断更，完结)
     */
    @PostMapping("/updateStatus")
    public R updateStatus(@RequestParam("bookId") long bookId,@RequestParam("status") String status){
        bookService.updateBookStatus(bookId,status);
        return R.ok();
    }

    /**
     * 读某一章节
     */
    @PostMapping("/readBook")
    public R<ReadBookRespVo> readBook(@RequestParam("bookId")Long bookId, @RequestParam(value = "chapter",required = false)Integer chapter) throws Exception {
        return R.ok(bookService.readBook(bookId,chapter));
    }

    @PostMapping("/getTotal")
    public R<PageResult<UserReadCountResp>> getTotal(@RequestBody UserReadCountReq req){
        return R.ok(bookService.totalReadingInfo(req));
    }


}
