package com.wujie.cart.contoller;


import com.wujie.cart.config.CartProperties;
import com.wujie.cart.service.CartService;
import com.wujie.common.pojo.Cart;
import com.wujie.common.util.ResultInfo;
import com.wujie.common.util.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CartProperties cartProperties;

    @RequestMapping("/findByUid")
    public ResultInfo findByUid(){
        String userId = UserContext.getUserId();
        List<Cart> list = cartService.findByUid(userId);
        ResultInfo res =  new ResultInfo();
        res.setData(list);
        res.setFlag(true);
        return res;
    }

    @GetMapping("/add")
    public String add(){
        System.out.println(cartProperties.getSize());
        return "success";
    }
}
