package com.taikang.filter;

import lombok.extern.slf4j.Slf4j;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 1
 * 2 * @Author: Lanvo
 * 3 * @Date: 2020/8/1 10:52
 * 4
 */
@Slf4j
public class LogCostFilter implements Filter {
    private HttpServletRequest request;


    @Override
    public void init(FilterConfig  filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        request=(HttpServletRequest)servletRequest;
        long start = System.currentTimeMillis();
        log.info("接口请求地址：{},开始处理。。。。。。",request.getRequestURL().toString());
         filterChain.doFilter(servletRequest,servletResponse);
        log.info("接口请求地址：{},处理时长{}",request.getRequestURL().toString(),String.valueOf((System.currentTimeMillis()-start)));
    }

    @Override
    public void destroy() {}

    }