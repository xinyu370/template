package com.example.demo.common.req;

import com.example.demo.common.pub.PageVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "UserReadCountReq",description = "")
public class UserReadCountReq extends PageVo {

    private String userName;
}
