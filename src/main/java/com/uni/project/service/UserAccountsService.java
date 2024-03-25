package com.uni.project.service;

import com.uni.project.dao.UserAccounts;
import com.uni.project.dto.AccountDto;
import com.uni.project.models.RequestCreateAccount;
import com.uni.project.models.RequestMakeTransfer;

import java.util.List;

public interface UserAccountsService {

    List<AccountDto> getAccountsOfUser(Long userId);

    Long getUserIdByPin(String pin);

    String createNewAccount(RequestCreateAccount requestBody);

    String makeTransfer(RequestMakeTransfer transferBody);

    List<UserAccounts> getAllActiveAccounts(Long userId);


}
