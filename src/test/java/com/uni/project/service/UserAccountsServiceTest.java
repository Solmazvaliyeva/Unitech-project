package com.uni.project.service;

import com.uni.project.dao.UserAccounts;
import com.uni.project.dao.UserEntity;
import com.uni.project.dao.repo.CurrencyRepository;
import com.uni.project.dao.repo.UserAccountRepository;
import com.uni.project.dao.repo.UserRepository;
import com.uni.project.models.AccountType;
import com.uni.project.models.BalanceAccount;
import com.uni.project.models.RequestMakeTransfer;
import com.uni.project.service.impl.UserAccountServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;


import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserAccountsServiceTest {
    private final Long userId = 1l;
    @Mock
    private UserRepository repository;

    @Mock
    private UserAccountRepository accountRepository;

    @Mock
    private CurrencyRepository currencyRepository;

    @InjectMocks
    private UserAccountServiceImpl userAccountService;
    private UserEntity user;
    private RequestMakeTransfer transferBody;


    @BeforeEach
    public void init() {

        user = UserEntity.builder().id(userId).pin("7bslc70").password("123").build();

        transferBody = RequestMakeTransfer.builder().senderIban("AZ01UBAZ001000001001")
                .receiverIban("AZ01UBAZ001000002001").amount(Double.valueOf(1)).build();


    }

    @Test
    public void UserAccountService_GetAllActiveAccounts() {
        Calendar calendar = Mockito.mock(Calendar.class);

        Integer accountSub1 = accountRepository.getMaxAccountNumber(user.getId()) + 1;

        UserAccounts account1 = UserAccounts.builder()
                .user(user)
                .accountSub(accountSub1)
                .accountOpenDate(calendar.getTime())
                .creditAmount(Double.valueOf(0))
                .debitAmount(Double.valueOf(0))
                .isActive('Y')
                .accountType(AccountType.CARI)
                .currency(currencyRepository.findByCurrencyName("AZN"))
                .build();

        UserAccounts account2 = UserAccounts.builder()
                .user(user)
                .accountSub(2)
                .accountOpenDate(calendar.getTime())
                .creditAmount(Double.valueOf(0))
                .debitAmount(Double.valueOf(0))
                .isActive('N')
                .accountType(AccountType.CARI)
                .currency(currencyRepository.findByCurrencyName("USD"))
                .build();


        lenient().when(repository.findById(userId)).thenReturn(Optional.of(user));
        lenient().when(accountRepository.listUserAccounts(userId)).thenReturn(Arrays.asList(account1, account2))
        ;
        List<UserAccounts> allActiveAccountsByUser = userAccountService.getAllActiveAccounts(userId);
        Assertions.assertThat(allActiveAccountsByUser).size().isGreaterThan(0);
    }


    @Test
    public void UserAccountService_MakeTransferBetweenAccounts() {
        Calendar calendar = Mockito.mock(Calendar.class);
        UserEntity userSender = user;
        UserEntity userReceiver = UserEntity.builder().id(2l).pin("5tyy67h").password("1234").build();

        UserAccounts accountSender = UserAccounts.builder()
                .user(userSender)
                .accountSub(1)
                .accountOpenDate(calendar.getTime())
                .creditAmount(Double.valueOf(6))
                .debitAmount(Double.valueOf(5))
                .isActive('Y')
                .accountType(AccountType.CARI)
                .currency(currencyRepository.findByCurrencyName("AZN"))
                .build();

        UserAccounts accountReceiver = UserAccounts.builder()
                .user(userReceiver)
                .accountSub(1)
                .accountOpenDate(calendar.getTime())
                .creditAmount(Double.valueOf(15))
                .debitAmount(Double.valueOf(7))
                .isActive('Y')
                .accountType(AccountType.CARI)
                .currency(currencyRepository.findByCurrencyName("AZN"))
                .build();
        userSender.setAccounts(Arrays.asList(accountSender));
        userReceiver.setAccounts(Arrays.asList(accountReceiver));


        BalanceAccount senderBalanceAccount = userAccountService.getAccount(transferBody.getSenderIban());
        BalanceAccount receiverBalanceAccount = userAccountService.getAccount(transferBody.getReceiverIban());

        lenient().when(repository.findById(userSender.getId())).thenReturn(Optional.of(userSender));
        lenient().when(accountRepository.getAccount(receiverBalanceAccount.getUserId(),
                receiverBalanceAccount.getAccountSub())).thenReturn(Optional.of(accountReceiver));

        lenient().when(accountRepository.getAccount(senderBalanceAccount.getUserId(),
                senderBalanceAccount.getAccountSub())).thenReturn(Optional.of(accountSender));


        lenient().when(accountRepository.save(Mockito.any(UserAccounts.class))).thenReturn(accountSender);
        lenient().when(accountRepository.save(Mockito.any(UserAccounts.class))).thenReturn(accountReceiver);

        assertEquals("successful transfer", userAccountService.makeTransfer(transferBody));
    }


}
