package com.taikang.tplatform_wechat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@EnableDiscoveryClient
@EnableEurekaClient
@EnableFeignClients
@MapperScan(basePackages={"com.taikang.tplatform_wechat.*.dao","com.taikang.tplatform_wechat.annuity.dao"})
@EnableCaching
@EnableAspectJAutoProxy
@SpringBootApplication
public class TplatformWechatApplication {

    public static void main(String[] args) {
        SpringApplication.run(TplatformWechatApplication.class, args);
    }

}
