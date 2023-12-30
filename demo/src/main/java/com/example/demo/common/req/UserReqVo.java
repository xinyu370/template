package com.example.demo.common.req;

import com.example.demo.common.pub.PageVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "UserReqVo",description = "查询用户请求Vo")
@Data
public class UserReqVo extends PageVo {

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
    @ApiModelProperty("暂时不用，采用物理删除")
    private String delFlag;
}
