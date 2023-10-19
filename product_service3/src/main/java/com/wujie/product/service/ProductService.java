package com.wujie.product.service;

import com.github.pagehelper.PageInfo;
import com.wujie.common.pojo.Product;

public interface ProductService {

    PageInfo<Product> find(Integer pageSize, Integer pageNum);

    Product findOne(String pid);
}
