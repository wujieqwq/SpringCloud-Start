package com.wujie.cart.service;


import com.wujie.common.pojo.Cart;

import java.util.List;

public interface CartService {
    List<Cart> findByUid(String uid);
}
