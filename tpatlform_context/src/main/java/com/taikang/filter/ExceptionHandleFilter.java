package com.taikang.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 1
 * 2 * @Author: Lanvo
 * 3 * @Date: 2020/8/1 11:55
 * 4 异常统一处理入口
 */
public class ExceptionHandleFilter implements Filter {
    private HttpServletRequest request;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
     public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest,servletResponse);
        }catch (Exception e){
            e.printStackTrace();
            servletResponse.getOutputStream().write("500:Service internal exception, please try again later ！".getBytes());
        }
     }
    @Override
    public void destroy() {

    }
}