package com.wujie.product.contoller;


import com.github.pagehelper.PageInfo;
import com.wujie.common.pojo.Product;
import com.wujie.common.util.ResultInfo;
import com.wujie.common.util.UserContext;
import com.wujie.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @RequestMapping("/find")
    public ResultInfo find(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize){
        PageInfo<Product> productPageInfo = productService.find(pageSize, pageNum);
        ResultInfo res =  new ResultInfo();
        res.setData(productPageInfo);
        res.setFlag(true);
        return res;
    }

    @RequestMapping("/findOne")
    public ResultInfo findOne(String pid){
        String userId = UserContext.getUserId();
        Product product = productService.findOne(pid);
        ResultInfo res =  new ResultInfo();
        res.setData(product);
        res.setFlag(true);
        return res;
    }
}
