package com.wujie.api.config;


import com.wujie.api.Interceptor.UserInfoInterceptor;
import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.HEADERS;
    }


    @Bean
    public RequestInterceptor getInterceptor(){
        return new UserInfoInterceptor();
    }
}
