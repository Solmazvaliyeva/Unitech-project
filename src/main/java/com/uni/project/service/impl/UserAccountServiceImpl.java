package com.uni.project.service.impl;

import com.uni.project.auth.exception.NoUserFoundException;
import com.uni.project.dao.*;
import com.uni.project.dao.repo.CurrencyRepository;
import com.uni.project.dao.repo.UserAccountRepository;
import com.uni.project.dao.repo.UserRepository;
import com.uni.project.dto.AccountDto;
import com.uni.project.dto.BalanceAccount;
import com.uni.project.dto.RequestCreateAccount;
import com.uni.project.dto.RequestMakeTransfer;
import com.uni.project.mapper.AccountMapper;
import com.uni.project.service.UserAccountsService;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAccountServiceImpl implements UserAccountsService {
    private final UserRepository repository;
    private final UserAccountRepository userAccountRepository;
    private final CurrencyRepository currencyRepository;


    public UserAccountServiceImpl(UserRepository repository, UserAccountRepository userAccountRepository, CurrencyRepository currencyRepository) {
        this.repository = repository;
        this.userAccountRepository = userAccountRepository;
        this.currencyRepository = currencyRepository;
    }

    @Override
    public List<AccountDto> getAccountsOfUser(Long userId) {
        var userEntity = repository.findById(userId).orElseThrow(() -> new NoUserFoundException("Entered User id not found"));

        // userEntity.getAccounts().stream().forEach(it -> System.out.println(it.getAccountCloseDate()));
        try {
            List<AccountDto> accounts = userEntity.getAccounts()
                    .stream().map(it -> AccountMapper.INSTANCE.accountEntityToDto(it))
                    .filter(it -> it.getIsActive().equals('Y'))
                    .collect(Collectors.toList());
            return accounts;
        } catch (NullPointerException exception) {
            throw new NoUserFoundException("There is not any active account");
        }
    }

    @Override
    public Long getUserIdByPin(String pin) {
        var userEntity = repository.findByPin(pin).orElseThrow(() -> new NoUserFoundException("Entered User pin not found"));
        return userEntity.getId();
    }

    @Override
    public String createNewAccount(RequestCreateAccount requestBody) {
        Calendar today = Calendar.getInstance();
        UserEntity userEntity = repository.findById(requestBody.getUserId()).orElseThrow(() -> new NoUserFoundException("Entered User id not found"));
        Integer accountSub = userAccountRepository.getMaxAccountNumber(userEntity.getId()) + 1;
        var userAccount = UserAccounts.builder()
                .user(userEntity)
                .accountSub(accountSub)
                .accountOpenDate(today.getTime())
                .creditAmount(Double.valueOf(0))
                .debitAmount(Double.valueOf(0))
                .isActive('Y')
                .accountType(requestBody.getAccountType())
                .currency(currencyRepository.findByCurrencyName(requestBody.getCurrency()))
                .build();

        userAccountRepository.save(userAccount);
        System.out.println("iban " + generateIban(userEntity.getId(), accountSub));
        return "successfully created";
    }

    @Override
    public String makeTransfer(RequestMakeTransfer transferBody) {
        String message = "";
        BalanceAccount senderBalanceAccount = getAccount(transferBody.getSenderIban());
        Double amount = userAccountRepository.getBalance(senderBalanceAccount.getUserId(), senderBalanceAccount.getAccountSub());
        BalanceAccount receiverBalanceAccount = getAccount(transferBody.getReceiverIban());

        if (transferBody.getAmount() > amount) {
            throw new RuntimeException("Don't have enough money in your  balance account");
        } else if (receiverBalanceAccount.getUserId() == senderBalanceAccount.getUserId() &
                receiverBalanceAccount.getAccountSub() == senderBalanceAccount.getAccountSub()) {
            throw new RuntimeException("You can't transfer to same account");

        } else if (!repository.findById(receiverBalanceAccount.getUserId()).isPresent()
                || !userAccountRepository.getAccount(receiverBalanceAccount.getUserId(), receiverBalanceAccount.getAccountSub()).isPresent()) {
            throw new RuntimeException("Receiver account doesn't exist");

        } else if (!userAccountRepository.isActive(receiverBalanceAccount.getUserId(), receiverBalanceAccount.getAccountSub())
                .isPresent()) {
            throw new RuntimeException("Receiver account is not active");
        }

        UserAccounts receiverAccount = userAccountRepository.getAccount(receiverBalanceAccount.getUserId() ,
                receiverBalanceAccount.getAccountSub()).orElseThrow();

        receiverAccount.setCreditAmount(transferBody.getAmount());
        userAccountRepository.save(receiverAccount);

        UserAccounts senderAccount = userAccountRepository.getAccount(senderBalanceAccount.getUserId(),
                senderBalanceAccount.getAccountSub()).orElseThrow();
        senderAccount.setDebitAmount(transferBody.getAmount());
        userAccountRepository.save(senderAccount);

        return "successful transfer";
    }

    public String generateIban(Long userId, Integer accountSub) {
        System.out.println("sub " + accountSub);
        StringBuilder id = new StringBuilder();
        for (int i = String.valueOf(userId).length(); i <= 6; i++) {
            id.append('0');
        }
        id.append(String.valueOf(userId));


        StringBuilder sub = new StringBuilder();
        for (int i = String.valueOf(accountSub).length(); i <= 3; i++) {
            sub.append('0');
        }
        sub.append(String.valueOf(accountSub));
        return "AZ01UBAZ001" + id + sub;
    }

    private BalanceAccount getAccount(String iban) {

        String customerNo = iban.substring(12, 17);
        String accountSub = iban.substring(17, 20);
        StringBuilder customer = new StringBuilder();
        StringBuilder account = new StringBuilder();

        for (int i = 0; i < customerNo.length(); i++) {
            if (customerNo.charAt(i) != '0') {
                customer.append(customerNo.substring(i));
                break;
            }
        }

        for (int i = 0; i < accountSub.length(); i++) {
            if (accountSub.charAt(i) != '0') {
                account.append(accountSub.substring(i));
                break;
            }
        }
        BalanceAccount balanceAccount = new BalanceAccount(Long.valueOf(customer.toString())
                , Integer.valueOf(account.toString()));
        return balanceAccount;
    }


}
