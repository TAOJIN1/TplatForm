package com.taikang.tplatformwechat;

import com.taikang.datasource.DynamicDataSourceRegister;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableCaching
@EnableAspectJAutoProxy
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication(exclude= DataSourceAutoConfiguration.class)
@MapperScan(basePackages={"com.taikang.policyproposal.*.dao"})
@EnableFeignClients
@Import(DynamicDataSourceRegister.class)
@EnableTransactionManagement
public class TplatformWechatApplication {

    public static void main(String[] args) {
        SpringApplication.run(TplatformWechatApplication.class, args);
    }

}
