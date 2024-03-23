package com.uni.project.dto;

public class BalanceAccount {
    private Long userId;
    private Integer accountSub;

    public BalanceAccount(Long userId, Integer accountSub) {
        this.userId = userId;
        this.accountSub = accountSub;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getAccountSub() {
        return accountSub;
    }

    public void setAccountSub(Integer accountSub) {
        this.accountSub = accountSub;
    }
}
