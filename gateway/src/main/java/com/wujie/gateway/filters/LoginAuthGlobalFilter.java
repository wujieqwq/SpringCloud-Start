package com.wujie.gateway.filters;

import com.wujie.gateway.config.AuthProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LoginAuthGlobalFilter implements GlobalFilter, Ordered {

    private final AuthProperties authProperties;
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
//        1.判断路径是否需要过滤
        if (isExclude(request)){
            return chain.filter(exchange);
        }
//        2.判断是否有token ->header的Authorization属性
        List<String> authorization = request.getHeaders().get("Authorization");
        if (authorization == null || authorization.isEmpty()){
            ServerHttpResponse response = exchange.getResponse();
            response.setRawStatusCode(401);
            return response.setComplete();
        }
        String token = authorization.get(0);
//        3.解析用户数据
        exchange.mutate()
                .request(builder -> builder.header("user-info", token))
                .build();



        return chain.filter(exchange);
    }

    public boolean isExclude(ServerHttpRequest request){
        RequestPath path = request.getPath();
        for (String s : authProperties.getExcludePath()) {
            if(antPathMatcher.match(s, String.valueOf(path))){
                return true;
            }
        }
        return false;
    }


    @Override
    public int getOrder() {
        return 1;
    }
}
