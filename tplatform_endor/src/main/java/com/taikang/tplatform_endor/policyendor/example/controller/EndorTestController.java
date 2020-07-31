package com.taikang.tplatform_endor.policyendor.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
    @GetMapping("/getStore/{id}")
    public String getStore(@PathVariable("id") String id){
        System.out.println("测试输出"+id);
        System.out.println("集中配置"+testValue);

        return "";
    }


}
