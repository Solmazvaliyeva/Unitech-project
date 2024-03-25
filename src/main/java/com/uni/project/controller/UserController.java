package com.uni.project.controller;

import com.uni.project.dto.AccountDto;
import com.uni.project.models.RequestCreateAccount;
import com.uni.project.models.RequestMakeTransfer;
import com.uni.project.service.UserAccountsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class UserController {

    private final  UserAccountsService service;

    public UserController(UserAccountsService service) {
        this.service = service;
    }

    @GetMapping("/getActiveAccounts/{id}")
    public ResponseEntity<List<AccountDto>> getActiveAccounts(@PathVariable Long id){
        return ResponseEntity.ok(service.getAccountsOfUser(id));
    }

    @GetMapping("/get-id/{pin}")
    public ResponseEntity<Long> getUserIdByPin(@PathVariable String pin){
        return ResponseEntity.ok(service.getUserIdByPin(pin));
    }

    @PostMapping("/create-user-account")

    public ResponseEntity<String> createUserAccount(@RequestBody RequestCreateAccount requestBody){
        return ResponseEntity.ok(service.createNewAccount(requestBody));
    }

    @PutMapping("/make-transfer")
    public ResponseEntity<String> makeTransfer(@RequestBody RequestMakeTransfer makeTransferBody){
        return ResponseEntity.ok(service.makeTransfer(makeTransferBody));
    }
}
