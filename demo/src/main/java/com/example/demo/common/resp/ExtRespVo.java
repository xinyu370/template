package com.example.demo.common.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "ExtRespVo",description = "通用对象返回req")
public class ExtRespVo {
    @ApiModelProperty("id")
    private Long id;
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
