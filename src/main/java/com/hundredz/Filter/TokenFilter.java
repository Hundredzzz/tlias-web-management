package com.hundredz.Filter;

<<<<<<< HEAD
import com.hundredz.utils.CurrentHolder;
import com.hundredz.utils.JwtUtils;
import io.jsonwebtoken.Claims;
=======
import com.hundredz.utils.JwtUtils;
>>>>>>> befb783f2ea6acee673c5b65508aad7c715ea16b
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 1. 获取请求路径
        String requestURL = request.getRequestURI();

        // 2. 判断是否时登陆操作
        if (requestURL.contains("/login")) {
            log.info("登录请求，放行");
            // 放行
            filterChain.doFilter(request, response);
            return;
        }

        // 3. 获取请求头token
        String token = request.getHeader("token");

        // 4. 判断token是否为空，如果不存在，说明用户没有登录，返回错误信息
        if (token == null || token.isEmpty()) {
            log.info("令牌为空，响应401状态码");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // 5. 如果存在，校验令牌，如果校验令牌失败， 返回错误信息
        try {
<<<<<<< HEAD
            Claims claims = JwtUtils.parseJWT(token);
            Integer empId = Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(empId);
            log.info("当前登录员工ID: {}, 将其存入ThreadLoacl", empId);


=======
            JwtUtils.parseJWT(token);
>>>>>>> befb783f2ea6acee673c5b65508aad7c715ea16b
        } catch (Exception e) {
            log.info("令牌为空，响应401状态码");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }


        // 6. 如果校验令牌成功，放行
        log.info("令牌校验成功，放行");
        filterChain.doFilter(request, response);
<<<<<<< HEAD

        // 7. 请求结束后，移除ThreadLocal中的数据
        CurrentHolder.remove();
=======
>>>>>>> befb783f2ea6acee673c5b65508aad7c715ea16b
    }
}
