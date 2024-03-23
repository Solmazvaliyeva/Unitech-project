package com.uni.project.dto;

import jakarta.persistence.Column;

import java.util.Date;

public class AccountDto {
    private Integer accountSub;
    private Date accountOpenDate;

    private Double creditAmount;
    private Double debitAmount;


    private Character isActive;

    public Integer getAccountSub() {
        return accountSub;
    }

    public void setAccountSub(Integer accountSub) {
        this.accountSub = accountSub;
    }

    public Date getAccountOpenDate() {
        return accountOpenDate;
    }

    public void setAccountOpenDate(Date accountOpenDate) {
        this.accountOpenDate = accountOpenDate;
    }


    public Character getIsActive() {
        return isActive;
    }

    public void setIsActive(Character isActive) {
        this.isActive = isActive;
    }

    public Double getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(Double creditAmount) {
        this.creditAmount = creditAmount;
    }

    public Double getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(Double debitAmount) {
        this.debitAmount = debitAmount;
    }
}
