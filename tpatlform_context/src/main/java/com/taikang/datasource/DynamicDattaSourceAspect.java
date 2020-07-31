package com.taikang.datasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author Lanvo
 * @Date 2019-03-23
 * @Description 动态数据源通知
 */
@Slf4j
@Aspect
@Order(-1)//保证在@Transactional之前执行
@Component
public class DynamicDattaSourceAspect {
    //改变数据源
    @Before("@annotation(targetDataSource)")
    public void changeDataSource(JoinPoint joinPoint, TargetDataSource targetDataSource) {
        String dbid = targetDataSource.name();

        if (!DynamicDataSourceContextHolder.isContainsDataSource(dbid)) {
            log.error("数据源 " + dbid + " 不存在使用默认的数据源 -> " + joinPoint.getSignature());
        } else {
            log.debug("使用数据源：" + dbid);
            DynamicDataSourceContextHolder.setDataSourceType(dbid);
        }
    }

    @After("@annotation(targetDataSource)")
    public void clearDataSource(JoinPoint joinPoint, TargetDataSource targetDataSource) {
        log.debug("清除数据源 " + targetDataSource.name() + " !");
        DynamicDataSourceContextHolder.clearDataSourceType();
    }
}
