package com.uni.project.mapper;

import com.uni.project.dao.UserAccounts;
import com.uni.project.dto.AccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);


    @Mapping(target = "accountSub" ,source = "accountSub")
    @Mapping(target = "accountOpenDate" ,source = "accountOpenDate")
    @Mapping(target = "creditAmount" ,source = "creditAmount")
    @Mapping(target = "debitAmount" ,source = "debitAmount")

    AccountDto  accountEntityToDto(UserAccounts userAccounts);

}
