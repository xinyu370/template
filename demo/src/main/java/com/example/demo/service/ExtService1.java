package com.example.demo.service;

import com.example.demo.common.pub.PageResult;
import com.example.demo.common.vo.ExtReqVo;
import com.example.demo.common.resp.ExtRespVo;
import com.example.demo.pojo.ExtPojo;


public interface ExtService1 {

    void addExt(ExtReqVo reqVo);

    ExtPojo getExtById(Long id);

    PageResult<ExtRespVo> getPageExt(ExtReqVo reqVo);

    void deleteExt(Long id);

    void updateExt(ExtReqVo reqVo);
}
