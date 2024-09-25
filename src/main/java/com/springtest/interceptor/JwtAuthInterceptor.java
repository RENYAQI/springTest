package com.springtest.interceptor;

import com.springtest.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class JwtAuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求头中的Token
        String token = request.getHeader("Authorization");
        System.out.println("已接收令牌: " + token); // Debug output

        // 如果Token为空，返回未登录错误
        if (token == null || token.isEmpty()) {
            System.out.println("未提供令牌，返回未经授权的错误."); // Debug output
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // 验证Token
        Claims claims = JwtTokenUtil.validateToken(token);
        if (claims == null) {
            System.out.println("令牌无效，返回未经授权的错误."); // Debug output
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // 如果验证成功，可以将用户信息放入request中，供后续使用
        request.setAttribute("username", claims.getSubject());
        System.out.println("已成功为用户验证令牌: " + claims.getSubject()); // Debug output

        return true; // 通过拦截器
    }
}
