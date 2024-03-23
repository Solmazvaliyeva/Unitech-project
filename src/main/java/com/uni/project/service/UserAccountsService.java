package com.uni.project.service;

import com.uni.project.dao.UserAccounts;
import com.uni.project.dto.AccountDto;
import com.uni.project.dto.RequestCreateAccount;
import com.uni.project.dto.RequestMakeTransfer;

import java.util.List;

public interface UserAccountsService {

    List<AccountDto> getAccountsOfUser(Long userId);

    Long getUserIdByPin(String pin);

    String createNewAccount(RequestCreateAccount requestBody);

    String makeTransfer(RequestMakeTransfer transferBody);


}
