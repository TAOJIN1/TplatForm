package com.taikang.policyendor.example.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.taikang.rpcclient.test.Dto.AccountTbl;
import com.taikang.rpcclient.test.feign.AccountServiceClient;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2 * @Author: Lanvo
 * 3 * @Date: 2020/7/30 15:09
 * 4
 */
@RestController
@RequestMapping("/test")
public class EndorTestController {
    @Value("${testValue}")
    private String testValue;
    /**
     * 根据id查询车辆的详细信息
     */
    @Autowired
    AccountServiceClient accountServiceClient;

    @GetMapping("/getStore/{id}")
    public String getStore(@PathVariable("id") String id){
        System.out.println("测试输出"+id);
        System.out.println("集中配置"+testValue);
        return "";
    }

    @GetMapping("testFeignClient")
    @HystrixCommand(fallbackMethod = "okFallback" )
    public List <AccountTbl> testFeignClient(){
       List <AccountTbl>list= accountServiceClient.queryAccountTbl();
       return  list;
    }
    public List <AccountTbl> okFallback() {
        System.out.println("进入熔断器了");
        throw new RuntimeException();
    }
}
