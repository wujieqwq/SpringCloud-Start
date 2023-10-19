package com.wujie.gateway.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "shop.auth")
@Configuration
public class AuthProperties {
    private List<String> includePath;
    private List<String> excludePath;
}
