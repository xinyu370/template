package com.example.demo.service;

import com.example.demo.common.pub.PageResult;
import com.example.demo.common.req.ProductReqVo;
import com.example.demo.pojo.Product;

public interface ProductService {


    void addProduct(Product product);

    PageResult<Product> getProductList(ProductReqVo reqVo);

    void updateProduct(Product product);

    void removeProduct(Long id);

}
