package com.taikang.policyproposal.example.service.impl;

import com.taikang.datasource.TargetDataSource;
import com.taikang.policyproposal.example.dao.AccountTblMapper;
import com.taikang.policyproposal.example.model.AccountTbl;
import com.taikang.policyproposal.example.model.AccountTblExample;
import com.taikang.policyproposal.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 1
 * 2 * @Author: Lanvo
 * 3 * @Date: 2020/8/1 3:07
 * 4
 */
@Service("accountService")
@CacheConfig(cacheNames = "UUser")
public class AccountServiceImpl  implements AccountService {

     @Autowired
     private  AccountTblMapper accountTblMapper;

     @TargetDataSource(name = "workflowDS")
     public List<AccountTbl> queryAccountTbl() {
         AccountTblExample accountTblExample=new AccountTblExample();
         List<AccountTbl> accountTblas=  accountTblMapper.selectByExample(accountTblExample);
        return accountTblas;
    }

    @TargetDataSource(name = "workflowDS")
    @Transactional
    public void saveAccountTbl() {
         AccountTbl accountTbl=new AccountTbl();
         accountTbl.setId(111);
         accountTbl.setMoney(2323);
         accountTbl.setUserId("32323");
         accountTblMapper.insert(accountTbl);
         throw  new RuntimeException();
    }

    @TargetDataSource(name = "workflowDS")
    @Cacheable(value = "UUser",key = "#key")
    public List<AccountTbl> cacheAccountTbl(String key) {
        AccountTblExample accountTblExample=new AccountTblExample();
        List<AccountTbl> accountTblas=  accountTblMapper.selectByExample(accountTblExample);
        return accountTblas;
     }
}