package com.taikang.rpcclient.test.feign;

import com.taikang.rpcclient.test.Dto.AccountTbl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 1
 * 2 * @Author: Lanvo
 * 3 * @Date: 2020/8/1 18:18
 * 4
 */
@FeignClient("tplatform-proposal")
public interface AccountServiceClient {
    @GetMapping("/test/testQuery")
    List<AccountTbl> queryAccountTbl();
}
