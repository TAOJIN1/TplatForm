package com.taikang.policyproposal.example.service;

import com.taikang.policyproposal.example.model.AccountTbl;

import java.util.List;

/**
 * 1
 * 2 * @Author: Lanvo
 * 3 * @Date: 2020/8/1 3:06
 * 4
 */
public interface AccountService {

    List<AccountTbl> queryAccountTbl();
    void saveAccountTbl();
}
