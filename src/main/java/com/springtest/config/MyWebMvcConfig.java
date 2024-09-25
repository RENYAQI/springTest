package com.springtest.config;

import com.springtest.interceptor.JwtAuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtAuthInterceptor())
                .addPathPatterns("/**") // 拦截所有路径
                .excludePathPatterns("/vue-element-admin/user/login", "/vue-element-admin/user/register", "/static/**"); // 放行登录、注册、静态资源
    }
}
