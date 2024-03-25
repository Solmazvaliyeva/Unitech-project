package com.uni.project.dao.repo;

import com.uni.project.dao.UserAccounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccounts,Long> {
    @Query("select max(accountSub) from UserAccounts a where a.user.id = :userId ")

    Integer getMaxAccountNumber(@Param("userId") Long userId);


    @Query("select creditAmount - debitAmount  from UserAccounts a where a.user.id = :userId and a.accountSub =:accountSub")

    Double getBalance(@Param("userId") Long userId ,@Param("accountSub") Integer accountSub);



    @Query("select a from UserAccounts a where a.user.id = :userId and a.accountSub =:accountSub")
    Optional<UserAccounts> getAccount(@Param("userId") Long userId , @Param("accountSub") Integer accountSub);
    @Query("select a from UserAccounts a where a.user.id = :userId")
    List<UserAccounts> listUserAccounts(@Param("userId") Long userId);

}
