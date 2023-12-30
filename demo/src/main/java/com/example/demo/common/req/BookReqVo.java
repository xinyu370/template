package com.example.demo.common.req;

import com.example.demo.common.pub.PageVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;


@Data
@ApiModel(value = "BookReqVo",description = "创建小说请求vo")
public class BookReqVo extends PageVo {
    private Long id;
    private String bookNames;
    private String author;
}
