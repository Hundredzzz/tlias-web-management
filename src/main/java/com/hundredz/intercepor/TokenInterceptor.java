package com.hundredz.intercepor;

import com.hundredz.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        // 1. 获取请求路径
//        String requestURL = request.getRequestURI();
//
//        // 2. 判断是否时登陆操作
//        if (requestURL.contains("/login")) {
//            log.info("登录请求，放行");
//            // 放行
//            return true;
//        }

        // 3. 获取请求头token
        String token = request.getHeader("token");

        // 4. 判断token是否为空，如果不存在，说明用户没有登录，返回错误信息
        if (token == null || token.isEmpty()) {
            log.info("令牌为空，响应401状态码");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // 5. 如果存在，校验令牌，如果校验令牌失败， 返回错误信息
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            log.info("令牌为空，响应401状态码");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }


        // 6. 如果校验令牌成功，放行
        log.info("令牌校验成功，放行");
        return true;
    }
}
