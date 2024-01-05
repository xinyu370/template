package com.example.demo.pojo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.req.ProductReqVo;
import com.example.demo.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper extends BaseMapper<Product> {

    List<Product> getPageList(Page<Product> page, @Param("vo") ProductReqVo vo);
}
