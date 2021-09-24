package com.uahage.api.config;

import com.uahage.api.interceptor.TokenVerifyInterceptor;
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
        registry.addInterceptor(new TokenVerifyInterceptor())
                .addPathPatterns("/users/{id}") // 회원 수정, 회원 삭제
                .addPathPatterns("/places/restaurants/reviews")
                .addPathPatterns("/places/restaurants/reviews/{reviewId}")
                .excludePathPatterns("/users/kakao-login")
                .excludePathPatterns("/users/naver-login");
    }
}