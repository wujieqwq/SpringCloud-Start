package com.wujie;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Unit test for simple App.
 */
@SpringBootTest
public class AppTest{

/*    @Autowired
    RestTemplate restTemplate;*/

    @Test
    public void test(){
        String uri = "http://product-service/product/findOne?pid=1";


    }
}
