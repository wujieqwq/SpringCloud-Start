package com.wujie.api.Interceptor;

import com.wujie.common.util.UserContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.util.StringUtils;

public class UserInfoInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        String userId = UserContext.getUserId();
        if(!StringUtils.isEmpty(userId)){
            requestTemplate.header("user-info",userId);
        }
    }
}
