package com.uni.project.dao;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "currency_table")
public class AccountCurrency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String currencyName;


    @OneToMany(mappedBy = "currency" )
    private List<UserAccounts> accounts;

    public AccountCurrency(Integer id, String currencyName, List<UserAccounts> accounts) {
        this.id = id;
        this.currencyName = currencyName;
        this.accounts = accounts;
    }

    public AccountCurrency() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public List<UserAccounts> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<UserAccounts> accounts) {
        this.accounts = accounts;
    }
}
