package com.example.demo.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


public class PageUtil {
    /**
     * 构建page 由于表单分页是从0开始的，所有这里统一都从0开始
     * @param pageNum
     * @param pageSize
     * @return
     */
    public static <T> Page<T> buildPage(Integer pageNum,Integer pageSize){
        pageNum = pageNum==null?0:pageNum;
        pageSize = pageSize==null?25:pageSize;
//        return new Page<>(pageNum+1,pageSize);
        // 2020年12月07日17 根据情况，这里调整为原始状态 从1 开始
        return new Page<>(pageNum,pageSize);
    }
}
