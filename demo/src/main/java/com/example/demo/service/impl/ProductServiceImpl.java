package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.BusinessException;
import com.example.demo.common.pub.PageResult;
import com.example.demo.common.req.ProductReqVo;
import com.example.demo.common.resp.UserReadCountResp;
import com.example.demo.pojo.Product;
import com.example.demo.pojo.mapper.ProductMapper;
import com.example.demo.pojo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import com.example.demo.utils.PageUtil;
import com.example.demo.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void addProduct(Product product) {
        if(StringUtils.isEmpty(product.getProductName())){
            throw new BusinessException("product name not null");
        }
        productRepository.save(product);
    }

    @Override
    public PageResult<Product> getProductList(ProductReqVo reqVo) {
        Page<Product> page = PageUtil.buildPage(reqVo.getPageNum(), reqVo.getPageSize());
        List<Product> pageList = productMapper.getPageList(page, reqVo);
        return PageResult.<Product>builder()
                .data(pageList)
                .count(page.getTotal())
                .build();
    }

    @Override
    public void updateProduct(Product product) {
        if(product.getId() == null){
            throw new BusinessException("update id not be bull");
        }
        productRepository.save(product);
    }

    @Override
    public void removeProduct(Long id) {
        productRepository.deleteById(id);
    }
}
