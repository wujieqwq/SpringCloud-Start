package com.wujie.product.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wujie.common.pojo.Product;
import com.wujie.product.mapper.ProductMapper;
import com.wujie.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    @Override
    public PageInfo<Product> find(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfo<>(productMapper.selectAll());
    }

    @Override
    public Product findOne(String pid) {
        return productMapper.selectByPrimaryKey(pid);
    }
}
