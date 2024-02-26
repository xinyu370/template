package com.example.demo.common.vo;

import com.example.demo.common.pub.PageVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "ExtReqVo",description = "通用对象请求req")
public class ExtReqVo extends PageVo {
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("标识符-请标识是属于那个业务模块(如课程管理、图书管理)")
    private String tag;
    @ApiModelProperty("通用字段(字符串)")
    private String ext1;
    @ApiModelProperty("通用字段(字符串)")
    private String ext2;
    @ApiModelProperty("通用字段(字符串)")
    private String ext3;
    @ApiModelProperty("通用字段(字符串)")
    private String ext4;
    @ApiModelProperty("通用字段(字符串)")
    private String ext5;
    @ApiModelProperty("通用字段(字符串)")
    private String ext6;
    @ApiModelProperty("通用字段(字符串)")
    private String ext7;
    @ApiModelProperty("通用字段(字符串)")
    private String ext8;
    @ApiModelProperty("通用字段(字符串)")
    private String ext9;
    @ApiModelProperty("通用字段(字符串)")
    private String ext10;
}
