package com.example.demo.controller;

import com.example.demo.common.R;
import com.example.demo.common.pub.PageResult;
import com.example.demo.common.req.ProductReqVo;
import com.example.demo.pojo.Product;
import com.example.demo.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "产品")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    @ApiOperation(value = "add产品")
    public R addProduct(@RequestBody Product product){
        productService.addProduct(product);
        return R.ok();
    }

    @PostMapping("/getProductList")
    @ApiOperation(value = "query产品")
    public R<PageResult<Product>> getProductList(@RequestBody ProductReqVo reqVo){
        return R.ok(productService.getProductList(reqVo));
    }

    @DeleteMapping("/deleteProduct")
    @ApiOperation(value = "delete产品")
    public R deleteProduct(@RequestParam("id") Long id){
        productService.removeProduct(id);
        return R.ok();
    }

    @PostMapping("/updateProduct")
    @ApiOperation(value = "update产品")
    public R updateProduct(@RequestBody Product product){
        productService.updateProduct(product);
        return R.ok();
    }

}
