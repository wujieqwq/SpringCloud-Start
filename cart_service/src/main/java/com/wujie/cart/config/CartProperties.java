package com.wujie.cart.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "shop.cart")
@Data
@Component
public class CartProperties {
    private Integer size;
}
