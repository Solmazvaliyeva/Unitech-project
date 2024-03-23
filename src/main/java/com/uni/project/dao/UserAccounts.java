package com.uni.project.dao;

import com.uni.project.dto.AccountType;
import jakarta.persistence.*;
import lombok.Builder;

import java.util.Date;


@Entity
@Table(name = "user_accounts")
@Builder
public class UserAccounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "account_sub")
    private Integer accountSub;

    @Column(name = "open_date")
    private Date accountOpenDate;

    @Column(name = "credit_amount")
    private Double creditAmount;
    @Column(name = "debit_amount")
    private Double debitAmount;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(name = "active")
    private Character isActive;

    @ManyToOne(cascade ={CascadeType.DETACH ,CascadeType.PERSIST ,CascadeType.MERGE ,
            CascadeType.REFRESH})
    @JoinColumn(columnDefinition = "user_id" ,referencedColumnName = "id" )
    private UserEntity user;



    @ManyToOne(cascade = {CascadeType.DETACH ,CascadeType.MERGE ,CascadeType.PERSIST ,CascadeType.REFRESH})
    @JoinColumn(columnDefinition = "currency_id" ,referencedColumnName = "id")
    private AccountCurrency currency;

    public UserAccounts() {
    }

    public UserAccounts(Long id, Integer accountSub, Date accountOpenDate, Double creditAmount, Double debitAmount, AccountType accountType, Character isActive, UserEntity user, AccountCurrency currency) {
        this.id = id;
        this.accountSub = accountSub;
        this.accountOpenDate = accountOpenDate;
        this.creditAmount = creditAmount;
        this.debitAmount = debitAmount;
        this.accountType = accountType;
        this.isActive = isActive;
        this.user = user;
        this.currency = currency;
    }

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

    public Character getIsActive() {
        return isActive;
    }

    public void setIsActive(Character isActive) {
        this.isActive = isActive;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public AccountCurrency getCurrency() {
        return currency;
    }

    public void setCurrency(AccountCurrency currency) {
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
