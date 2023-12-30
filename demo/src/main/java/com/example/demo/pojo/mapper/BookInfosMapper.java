package com.example.demo.pojo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.req.BookReqVo;
import com.example.demo.common.req.UserReadCountReq;
import com.example.demo.common.resp.BookRespVo;
import com.example.demo.common.resp.UserReadCountResp;
import com.example.demo.pojo.BookInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookInfosMapper extends BaseMapper<BookInfo> {
    List<BookRespVo> getBookInfos(Page<BookRespVo> page, @Param("vo") BookReqVo vo);

    List<UserReadCountResp> getTotalReadInfos(Page<UserReadCountResp> page, @Param("vo") UserReadCountReq vo);

    List<UserReadCountResp> getTotalCompelet(@Param("vo") List<String> vo,@Param("status") String status);

    List<UserReadCountResp> getTotalReadChapter(@Param("vo") List<String> userNames);


}
