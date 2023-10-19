package com.wujie.api.feign;

import com.wujie.common.util.ResultInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "product-service")
public interface ProductFeign {

    @RequestMapping("/product/findOne")
    ResultInfo getProductByPid(@RequestParam String pid);
}