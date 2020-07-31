package com.taikang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableCaching
@EnableAspectJAutoProxy
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication(exclude=DataSourceAutoConfiguration.class)
public class TplatformEndorApplication {

    public static void main(String[] args) {
        SpringApplication.run(TplatformEndorApplication.class, args);
    }

}
