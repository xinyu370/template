package com.example.demo.common.req;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "ChapterInfo",description = "章节请求返回vo")
public class ChapterInfo implements Serializable {
    private static final long serialVersionUID = -1L;

    private Long bookId;

    private Integer chapterNum;

    private String currentChapterName;

    private String content;
}
