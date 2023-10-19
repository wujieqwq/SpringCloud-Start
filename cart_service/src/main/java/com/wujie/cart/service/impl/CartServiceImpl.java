package com.wujie.cart.service.impl;


import com.wujie.api.feign.ProductFeign;
import com.wujie.cart.mapper.CartMapper;
import com.wujie.cart.service.CartService;
import com.wujie.common.pojo.Cart;
import com.wujie.common.util.ResultInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.LinkedHashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartMapper cartMapper;

/*    private final RestTemplate restTemplate;

    private final DiscoveryClient client;

    private final LoadBalancerClient loadBalancerClient;*/

    private final ProductFeign productFeign;

    @Override
    public List<Cart> findByUid(String uid) {
        Example example = new Example(Cart.class);
        example.createCriteria().andEqualTo("uid",uid);
        List<Cart> carts = cartMapper.selectByExample(example);
        for (Cart cart : carts) {
/*            String pid = cart.getPid();
            Product product = productMapper.selectByPrimaryKey(pid);
            cart.setDifferencePrice(cart.getPrice()-product.getPrice());

            String uri = "http://localhost:8100/product/findOne?pid="+cart.getPid();
            1.简单直接给随机数来均衡负载
            List<ServiceInstance> instances = client.getInstances("product_service");
            ServiceInstance serviceInstance = instances.get(RandomUtils.nextInt(0,instances.size()));
            2.通过loadBalanceCilent
            ServiceInstance instance = loadBalancerClient.choose("product-service")
            String uri = instance.getUri()+"/product/findOne?pid="+cart.getPid();
            3.交给restTemplate 注解@loadBalanced*/
/*            String uri = "http://product-service/product/findOne?pid="+cart.getPid();

            ResultInfo res = restTemplate.getForObject(uri, ResultInfo.class);*/
            ResultInfo res = productFeign.getProductByPid(cart.getPid());
            if (res != null) {
                LinkedHashMap map = (LinkedHashMap) res.getData();
                cart.setDifferencePrice(cart.getPrice()-(Double) map.get("price"));
            }
        }
        return carts;

    }
}
