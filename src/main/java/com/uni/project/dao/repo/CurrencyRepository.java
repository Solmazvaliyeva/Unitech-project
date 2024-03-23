package com.uni.project.dao.repo;

import com.uni.project.dao.AccountCurrency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<AccountCurrency,Integer> {
    AccountCurrency findByCurrencyName(String currencyName);
}
