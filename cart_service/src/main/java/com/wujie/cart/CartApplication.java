package com.wujie.cart;

import com.wujie.api.config.FeignConfig;
import com.wujie.common.annotation.EnableInterceptorConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.wujie.api",defaultConfiguration = FeignConfig.class)
@EnableInterceptorConfiguration
public class CartApplication {

    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class, args);
    }

/*    @Bean
    @LoadBalanced //本质拦截器
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }*/

}
