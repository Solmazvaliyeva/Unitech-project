package com.uni.project.dto;

public class RequestCreateAccount {

    private Long userId;

    private AccountType accountType;
    private String currency;


    public RequestCreateAccount(Long userId, AccountType accountType, String currency) {
        this.userId = userId;
        this.accountType = accountType;
        this.currency = currency;
    }

    public RequestCreateAccount() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
