package com.example.demo.pojo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.req.UserReqVo;
import com.example.demo.common.resp.UserRespVo;
import com.example.demo.pojo.MyUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyUserMapper extends BaseMapper<MyUser> {

    public List<UserRespVo> getUserList(@Param("page") Page<UserRespVo> page, @Param("vo") UserReqVo vo);

}
