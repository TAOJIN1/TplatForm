package com.taikang.config;

import com.taikang.filter.ExceptionHandleFilter;
import com.taikang.filter.LogCostFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * 1
 * 2 * @Author: Lanvo
 * 3 * @Date: 2020/8/1 10:51
 * 4
 * note:过滤器生成器
 */
@Configuration
public class FilterGenarate {
    /**
     * 接口请求监控
     * @return
     */
    @Bean
    public FilterRegistrationBean registLogCostFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean<LogCostFilter>();
        registration.setFilter(new LogCostFilter());
        registration.addUrlPatterns("/*");
        registration.setName("LogCostFilter");
        registration.setOrder(1);
        return registration;
    }

    /****
     * 字符集过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean registCharacterEncodingFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean<CharacterEncodingFilter>();
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setForceEncoding(true);
        characterEncodingFilter.setEncoding("UTF-8");
        registration.setFilter(characterEncodingFilter);
        registration.addUrlPatterns("/*");
        return registration;
    }

    /****
     *  异常统一处理类处理入口过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean registExceptionHandleFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean<ExceptionHandleFilter>();
        registration.setFilter(new ExceptionHandleFilter());
        registration.addUrlPatterns("/*");
        registration.setName("ExceptionHandleFilter");
        registration.setOrder(1);
        return registration;
    }
}
