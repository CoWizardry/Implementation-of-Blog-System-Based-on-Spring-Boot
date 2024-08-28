package com.site.blog.my.core.config;

import com.site.blog.my.core.interceptor.AdminLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyBlogWebMvcConfigurer implements WebMvcConfigurer {

    // 自动注入自定义的 AdminLoginInterceptor 拦截器
    @Autowired
    private AdminLoginInterceptor adminLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加拦截器，拦截 /admin/** 路径下的请求
        registry.addInterceptor(adminLoginInterceptor)
                .addPathPatterns("/admin/**")  // 拦截以 /admin 开头的所有路径
                .excludePathPatterns("/admin/login")  // 排除登录页面路径
                .excludePathPatterns("/admin/dist/**")  // 排除静态资源路径
                .excludePathPatterns("/admin/plugins/**");  // 排除插件资源路径
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 定义静态资源映射，将 /upload/** 映射到本地文件系统
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:" + Constants.FILE_UPLOAD_DIC);  // 映射到本地文件上传目录
    }
}

