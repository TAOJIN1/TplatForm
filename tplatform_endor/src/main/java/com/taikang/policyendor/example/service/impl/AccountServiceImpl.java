package com.taikang.policyendor.example.service.impl;

import com.taikang.datasource.TargetDataSource;
import com.taikang.policyendor.example.dao.AccountTblMapper;
import com.taikang.policyendor.example.model.AccountTbl;
import com.taikang.policyendor.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 1
 * 2 * @Author: Lanvo
 * 3 * @Date: 2020/8/1 3:07
 * 4
 */
@Service("accountServiceImpl")
public class AccountServiceImpl  implements AccountService {

     @Autowired
     AccountTblMapper accountTblMapper;

    @TargetDataSource(name = "")
     public List<AccountTbl> queryAccountTbl() {
        return null;
    }
}
