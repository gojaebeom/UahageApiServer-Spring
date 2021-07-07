package kr.co.hohocompany.uahage.config;

import kr.co.hohocompany.uahage.interceptor.OauthKakaoInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new OauthKakaoInterceptor())
                .addPathPatterns("/api/users/kakao-login");
    }
}
