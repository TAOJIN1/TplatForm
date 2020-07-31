package com.taikang.tpatform_eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class TpatformEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TpatformEurekaApplication.class, args);
    }

}
