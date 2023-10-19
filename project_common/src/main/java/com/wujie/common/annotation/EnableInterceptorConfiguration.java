package com.wujie.common.annotation;


import com.wujie.common.WebConfig.config;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({config.class})
public @interface EnableInterceptorConfiguration {

}
