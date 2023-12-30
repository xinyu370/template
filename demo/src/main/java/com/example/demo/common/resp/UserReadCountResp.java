package com.example.demo.common.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "UserReadCountResp",description = "用户阅读统计返回Vo")
public class UserReadCountResp implements Serializable {
    private static final long serialVersionUID = -1L;

    @ApiModelProperty("用户姓名")
    private String userName;
    @ApiModelProperty("写了多少本")
    private Long totalWrite;
    @ApiModelProperty("完结多少本")
    private Long totalComplete;
    @ApiModelProperty("在写多少本")
    private Long totalWriting;
    @ApiModelProperty("写了多少字")
    private Long totalWriteWord;
    @ApiModelProperty("看了多少本")
    private Long totalLook;
    @ApiModelProperty("看了多少章节")
    private Long totalLookChapter;
}
