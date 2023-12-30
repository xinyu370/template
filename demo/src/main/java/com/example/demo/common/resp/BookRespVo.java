package com.example.demo.common.resp;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "BookRespVo",description = "书籍列表返回VO")
public class BookRespVo  implements Serializable {
    private static final long serialVersionUID = -1L;

    private Long id;
    private String bookNames;
    private String author;
    private Integer chapters;

}
