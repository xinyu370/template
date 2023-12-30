package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.BusinessException;
import com.example.demo.common.enums.BookStatusEnum;
import com.example.demo.common.pub.PageResult;
import com.example.demo.common.req.BookReqVo;
import com.example.demo.common.req.ChapterInfo;
import com.example.demo.common.req.UserReadCountReq;
import com.example.demo.common.resp.BookRespVo;
import com.example.demo.common.resp.ReadBookRespVo;
import com.example.demo.common.resp.UserReadCountResp;
import com.example.demo.pojo.BookInfo;
import com.example.demo.pojo.ReadBookInfo;
import com.example.demo.pojo.mapper.BookInfosMapper;
import com.example.demo.pojo.repository.BookInfoRepository;
import com.example.demo.pojo.repository.ReadBookInfoRepository;
import com.example.demo.service.BookService;
import com.example.demo.utils.PageUtil;
import com.example.demo.utils.StringUtils;
import com.example.demo.utils.UserCurrentUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    @Autowired
    private BookInfoRepository bookInfoRepository;

    @Autowired
    private BookInfosMapper bookInfosMapper;

    @Autowired
    private ReadBookInfoRepository readBookInfoRepository;
    @Override
    @Transactional
    public void createBook(BookReqVo reqVo) throws IOException {
        if(StringUtils.isEmpty(reqVo.getBookNames()) || StringUtils.isEmpty(reqVo.getAuthor())){
            throw new BusinessException("小说名和小说作者不能为空");
        }
        List<BookInfo> bookInfoByAuthorAndAndBookNames = bookInfoRepository.getBookInfoByAuthorAndAndBookNames(reqVo.getAuthor(), reqVo.getBookNames());
        if(!CollectionUtils.isEmpty(bookInfoByAuthorAndAndBookNames)){
            throw new BusinessException("你已经创建了一本相同名称的小说");
        }

        BookInfo bookInfo = new BookInfo();
        String path = "D:/BOOKS/"+reqVo.getBookNames()+"/";
        Path dir = Paths.get(path);
        Files.createDirectories(dir);

//        File file = new File(path+"");
//        file.createNewFile();
        bookInfo.setBookDir(path);
        bookInfo.setBookNames(reqVo.getBookNames());
        bookInfo.setAuthor(reqVo.getAuthor());
        bookInfo.setStatus(BookStatusEnum.NEW_STATUS.toString());
        bookInfo.setChapters(0);
        bookInfoRepository.save(bookInfo);
    }

    @Override
    @Transactional
    public void addChapter(ChapterInfo req) throws IOException {
        Supplier<BusinessException> supplier = ()->new BusinessException("根据id未获取到小说信息");
        BookInfo bookInfo = bookInfoRepository.findById(req.getBookId()).orElseThrow(supplier);
        Integer chapters = bookInfo.getChapters()+1;
        FileWriter fw = null;
        try{
            File file = new File(bookInfo.getBookDir() + "/" + chapters + ".txt");
            file.createNewFile();
            fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(chapters+"."+req.getCurrentChapterName()+"^v^\n");
            bw.write(req.getContent()+"\n");
            bw.flush();
        }catch (IOException e){
            throw new BusinessException("添加章节失败");
        }finally {
            fw.flush();
            fw.close();
        }
        Long words = bookInfo.getTotalWord()==null?0:bookInfo.getTotalWord();
        bookInfo.setTotalWord(words+req.getContent().length());
        bookInfo.setStatus(BookStatusEnum.WRITING_STATUS.toString());
        bookInfo.setChapters(chapters);
        bookInfoRepository.save(bookInfo);
    }

    @Override
    public PageResult<BookRespVo> getBookList(BookReqVo reqVo) {
        Page<BookRespVo> page = PageUtil.buildPage(reqVo.getPageNum(), reqVo.getPageSize());
         List<BookRespVo> bookInfos = bookInfosMapper.getBookInfos(page, reqVo);
        return PageResult.<BookRespVo>builder()
                .data(bookInfos)
                .count(page.getTotal())
                .build();
    }

    @Override
    public void updateBookStatus(long bookId, String status) {
        Supplier<BusinessException> ex = ()->new BusinessException("根据id未获取到书籍信息");
        BookInfo bookInfo = bookInfoRepository.findById(bookId).orElseThrow(ex);
        bookInfo.setStatus(status);
        bookInfoRepository.save(bookInfo);
    }

    @Override
    public ReadBookRespVo readBook(long bookId, Integer chapter) throws Exception {
        ReadBookRespVo resp = new ReadBookRespVo();
        String currentUserName = UserCurrentUtils.getCurrentUserName();
        BookInfo bookInfo = bookInfoRepository.getById(bookId);
        if(bookInfo==null){
            throw new BusinessException("未查到书籍信息");
        }
        ReadBookInfo readBookInfo = readBookInfoRepository.findByBookIdAndUserName(bookId, currentUserName).orElse(null);
        int c = chapter==null?1:chapter;
        if(readBookInfo != null && chapter==null){
            c = readBookInfo.getLastChapter();
            readBookInfo.setLastChapter(c);
        }else{
            readBookInfo = readBookInfo==null ?new ReadBookInfo():readBookInfo ;
            readBookInfo.setBookId(bookId);
            readBookInfo.setLastChapter(c);
            readBookInfo.setUserName(currentUserName);
        }
        readBookInfoRepository.save(readBookInfo);
        resp.setChapter(c);
        resp.setBookNames(bookInfo.getBookNames());
        resp.setAuthor(bookInfo.getAuthor());
        resp.setStatus(bookInfo.getStatus());
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(new File(bookInfo.getBookDir()+c+".txt"));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while((line = bufferedReader.readLine())!=null){
                resp.setContent(resp.getContent()+line);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resp;
    }

    @Override
    public PageResult<UserReadCountResp> totalReadingInfo(UserReadCountReq req) {
        Page<UserReadCountResp> page = PageUtil.buildPage(req.getPageNum(), req.getPageSize());
        //看了多少本，写了多少字
        List<UserReadCountResp> totalReadInfos = bookInfosMapper.getTotalReadInfos(page, req);
        if(!CollectionUtils.isEmpty(totalReadInfos)){
            List<String> userNames = totalReadInfos.stream().map(UserReadCountResp::getUserName).collect(Collectors.toList());
            Map<String, UserReadCountResp> completeMap = bookInfosMapper.getTotalCompelet(userNames, BookStatusEnum.COMPLETE_STATUS.toString()).stream().collect(Collectors.toMap(UserReadCountResp::getUserName, a -> a));
            Map<String, UserReadCountResp> totalWriteMap = bookInfosMapper.getTotalCompelet(userNames,BookStatusEnum.WRITING_STATUS.toString()).stream().collect(Collectors.toMap(UserReadCountResp::getUserName, a -> a));
            Map<String, UserReadCountResp> totalReadChapter = bookInfosMapper.getTotalReadChapter(userNames).stream().collect(Collectors.toMap(UserReadCountResp::getUserName, a -> a));
            for(UserReadCountResp ele : totalReadInfos){
                String userName = ele.getUserName();
                ele.setTotalComplete(completeMap.containsKey(userName)?completeMap.get(userName).getTotalComplete():0);
                ele.setTotalWriting(totalWriteMap.containsKey(userName)?totalWriteMap.get(userName).getTotalComplete():0);
                ele.setTotalLookChapter(totalReadChapter.containsKey(userName)?totalReadChapter.get(userName).getTotalLookChapter():0);
                ele.setTotalWrite(ele.getTotalWrite()==null?0:ele.getTotalWrite());
                ele.setTotalLook(ele.getTotalLook()==null?0:ele.getTotalLook());
                ele.setTotalWriteWord(ele.getTotalWriteWord()==null?0: ele.getTotalWriteWord());
            }
        }
        return PageResult.<UserReadCountResp>builder()
                .data(totalReadInfos)
                .count(page.getTotal())
                .build();
    }

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("D:/BOOKS/"+"肖申克的救赎"+"/"+"肖申克的救赎.txt");
         Path directories = Files.createFile(path);
    }
}
