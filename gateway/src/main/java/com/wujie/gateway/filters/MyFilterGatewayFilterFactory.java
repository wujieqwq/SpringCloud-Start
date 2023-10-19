package com.wujie.gateway.filters;

import lombok.Data;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;


@Component
public class MyFilterGatewayFilterFactory extends AbstractGatewayFilterFactory<MyFilterGatewayFilterFactory.Config> {

    public MyFilterGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                System.out.println("myFilter");
                System.out.println(config.getA());
                System.out.println(config.getB());
                System.out.println(config.getC());

                return chain.filter(exchange);
            }
        };
    }


    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("a","b","c");
    }

    @Data
    public static class Config{
        private String a;
        private String b;
        private String c;
    }

}
