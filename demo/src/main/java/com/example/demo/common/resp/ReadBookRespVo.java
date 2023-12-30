package com.example.demo.common.resp;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "ReadBookRespVo",description = "书籍阅读请求返回Vo")
public class ReadBookRespVo  implements Serializable {
    private static final long serialVersionUID = -1L;

    private Integer chapter;

    private String currentChapterName;

    private String content;

    private String bookNames;

    private String author;

    private String  status;

}
