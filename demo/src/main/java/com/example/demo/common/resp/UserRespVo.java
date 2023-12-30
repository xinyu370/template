package com.example.demo.common.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "UserRespVo",description = "查询用户返回Vo")
public class UserRespVo implements Serializable {
    private static final long serialVersionUID = -1L;
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("电话")
    private String phoneNumber;
    //0代表用户，1代表管理员
    @ApiModelProperty("角色:0代表用户，1代表管理员")
    private String tenantCode;
    //0禁用，1启用
    @ApiModelProperty("启用：0禁用，1启用")
    private String enabled;
//    @ApiModelProperty("暂时不用，采用物理删除")
//    private String delFlag;
}
