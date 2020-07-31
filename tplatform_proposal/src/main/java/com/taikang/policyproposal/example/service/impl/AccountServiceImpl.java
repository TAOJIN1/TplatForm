package com.taikang.policyproposal.example.service.impl;

import com.taikang.datasource.TargetDataSource;
import com.taikang.policyproposal.example.dao.AccountTblMapper;
import com.taikang.policyproposal.example.model.AccountTbl;
import com.taikang.policyproposal.example.model.AccountTblExample;
import com.taikang.policyproposal.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 1
 * 2 * @Author: Lanvo
 * 3 * @Date: 2020/8/1 3:07
 * 4
 */
@Service("accountService")
public class AccountServiceImpl  implements AccountService {

     @Autowired
     private  AccountTblMapper accountTblMapper;

     @TargetDataSource(name = "workflowDS")
     public List<AccountTbl> queryAccountTbl() {
         AccountTblExample accountTblExample=new AccountTblExample();
        List<AccountTbl> accountTblas=  accountTblMapper.selectByExample(accountTblExample);
        return accountTblas;
    }
}
