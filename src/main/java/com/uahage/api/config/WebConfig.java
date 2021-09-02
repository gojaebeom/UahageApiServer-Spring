package com.uahage.api.config;

import com.uahage.api.interceptor.Oauth2TokenVerifyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new Oauth2TokenVerifyInterceptor())
                .addPathPatterns("/users/**-login");

//        registry.addInterceptor(new TokenVerifyInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/users/kakao-login")
//                .excludePathPatterns("/users/naver-login");
    }
}