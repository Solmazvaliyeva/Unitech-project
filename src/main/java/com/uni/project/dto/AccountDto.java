package com.uni.project.dto;

import com.uni.project.models.AccountType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;

import java.util.Date;
@Builder
public class AccountDto {
    private Integer accountSub;
    private Date accountOpenDate;

    private Double creditAmount;
    private Double debitAmount;


    private Character isActive;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;



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

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }


}
