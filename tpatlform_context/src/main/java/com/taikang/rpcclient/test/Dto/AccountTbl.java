package com.taikang.rpcclient.test.Dto;

public class AccountTbl {
    private Integer id;

    private String userId;

    private Integer money;

    public AccountTbl(Integer id, String userId, Integer money) {
        this.id = id;
        this.userId = userId;
        this.money = money;
    }

    public AccountTbl() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}