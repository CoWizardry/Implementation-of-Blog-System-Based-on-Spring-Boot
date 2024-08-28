package com.site.blog.my.core.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AdminLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        // 如果 session 中没有登录用户信息
        if (null == request.getSession().getAttribute("loginUser")) {
            // 设置错误消息提示用户重新登录
            request.getSession().setAttribute("errorMsg", "请重新登陆");
            // 重定向到登录页面
            response.sendRedirect(request.getContextPath() + "/admin/login");
            return false;  // 拦截请求，防止继续执行
        } else {
            // 移除错误消息，允许请求继续
            request.getSession().removeAttribute("errorMsg");
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        // 方法留空，处理完请求后不执行任何操作
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
        // 方法留空，请求完成后不执行任何操作
    }
}

