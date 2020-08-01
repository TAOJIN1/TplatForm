package com.taikang.policyproposal.example.controller;

import com.taikang.policyproposal.example.model.AccountTbl;
import com.taikang.policyproposal.example.service.AccountService;
import com.taikang.redisrelated.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 2 * @Author: Lanvo
 * 3 * @Date: 2020/7/30 15:09
 * 4
 */
@RestController
@RequestMapping("/test")
public class ProposalTestController {


    @Value("${testValue}")
    private String testValue;

    @Autowired
    private AccountService accountService;

    @Autowired
    RedisClient redisClient;
    @GetMapping("/getStore/{id}")
    public String getStore(@PathVariable("id") String id) {
        System.out.println("测试输出" + id);
        System.out.println("集中配置" + testValue);
        return "";
    }


    @GetMapping("/testQuery")
    public List<AccountTbl> testQuery() {
        return accountService.queryAccountTbl();
    }

    @GetMapping("/exception")
    public void exception() {
       throw new RuntimeException();
    }

    @GetMapping("testTransction")
    public void testTransction() {
         accountService.saveAccountTbl();
    }


    @GetMapping("testForRedis")
    public Object testForRedis(){
        redisClient.set("test",23423);
        return  redisClient.get("test");

    }
}
