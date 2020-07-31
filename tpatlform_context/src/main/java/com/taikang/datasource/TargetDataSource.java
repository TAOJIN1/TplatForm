package com.taikang.datasource;

import java.lang.annotation.*;

/**
 * @Author Lanvo
 * @Date 2019-03-23
 * @Description 作用于类、接口或者方法上
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {

    String name();
}
