package com.taikang.policyproposal.example.controller;

import com.taikang.config.CorsConfig;
import com.taikang.policyproposal.example.model.AccountTbl;
import com.taikang.policyproposal.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

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
    /**
     * 根据id查询车辆的详细信息
     */
    @GetMapping("/getStore/{id}")
    public String getStore(@PathVariable("id") String id) {
        System.out.println("测试输出" + id);
        System.out.println("集中配置" + testValue);
        CorsConfig config = new CorsConfig();
        config.addCorsMappings(new CorsRegistry());
        return "";
    }

    /**
     * 根据id查询车辆的详细信息
     */
    @GetMapping("/testQuery")
    public List<AccountTbl> testQuery() {
        return accountService.queryAccountTbl();

    }


}
