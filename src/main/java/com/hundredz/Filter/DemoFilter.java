package com.hundredz.Filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*") // 拦截所有请求
@Slf4j
public class DemoFilter implements Filter {
    // 初始化方法，web服务器启动的时候执行一次，只执行一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init 初始化方法...");
    }

    // 拦截到请求之后执行，会执行多次
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("拦截到了请求... 放行前的逻辑运行了");
        // 放行
        filterChain.doFilter(servletRequest, servletResponse);

        log.info("拦截到了请求... 放行后的逻辑运行了");
    }

    // 销毁方法，web服务器关闭的时候执行一次，只执行一次
    @Override
    public void destroy() {
        log.info("destroy 销毁方法...");
    }
}
