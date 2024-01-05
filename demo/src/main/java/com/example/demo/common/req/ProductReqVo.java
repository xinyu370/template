package com.example.demo.common.req;

import com.example.demo.common.pub.PageVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@ApiModel(value = "productReqVo",description = "产品分页查询vo")
public class ProductReqVo extends PageVo {

    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("productName")
    private String productName;
    @ApiModelProperty("productCode")
    private String productCode;
}
