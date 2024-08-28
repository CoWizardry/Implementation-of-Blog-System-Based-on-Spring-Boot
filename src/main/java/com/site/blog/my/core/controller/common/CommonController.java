package com.site.blog.my.core.controller.common;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CommonController {

    // 映射请求路径 "/common/kaptcha" 到此方法
    @GetMapping("/common/kaptcha")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        // 设置响应头，禁止缓存验证码图片
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/png"); // 设置响应内容类型为 PNG 图片

        // 生成一个 150x30 大小、4 位字符、干扰线宽度为 2 的验证码
        ShearCaptcha shearCaptcha = CaptchaUtil.createShearCaptcha(150, 30, 4, 2);

        // 将验证码存入 session，供后续验证使用
        httpServletRequest.getSession().setAttribute("verifyCode", shearCaptcha);

        // 输出验证码图片到响应流
        shearCaptcha.write(httpServletResponse.getOutputStream());
    }
}


