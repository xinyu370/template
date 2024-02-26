package com.example.demo.pojo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.vo.ExtReqVo;
import com.example.demo.common.resp.ExtRespVo;
import com.example.demo.pojo.ExtPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtMapper extends BaseMapper<ExtPojo> {
    List<ExtRespVo> getExtPageList(@Param("page") Page<ExtRespVo> page, @Param("vo") ExtReqVo vo);
}
