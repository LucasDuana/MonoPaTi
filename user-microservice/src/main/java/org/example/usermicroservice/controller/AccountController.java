package org.example.usermicroservice.controller;


import org.example.usermicroservice.dtos.AccountDTO;
import org.example.usermicroservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    //Basic CRUD operations for Account entity
    //GET /accounts

    @GetMapping("")
    public ResponseEntity<List<AccountDTO>> getAccounts(){
        return ResponseEntity.ok(this.accountService.getAllAccounts());
    }

    //PATCH /id
    //Set enabled an account
    @PatchMapping("/{id}/enabled")
    public AccountDTO setAccountEnabled(@PathVariable Long id) {
        return accountService.setAccountEnabled(id);
    }

    //PATCH /id
    //Set disabled an account
    @PatchMapping("/{id}/disabled")
    public AccountDTO setAccountDisabled(@PathVariable Long id) {
        return accountService.setAccountDisabled(id);
    }

    //GET /{id}
    @GetMapping("/{id}")
    public AccountDTO getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    //POST
    @PostMapping
    public AccountDTO createAccount(@RequestBody AccountDTO accountDTO) {
        return accountService.createAccount(accountDTO);
    }
    //PUT /{id}
    @PutMapping("/{id}")
    public AccountDTO updateAccount(@PathVariable Long id,@RequestBody AccountDTO accountDTO) {
        return accountService.updateAccount(id,accountDTO);
    }
    //DELETE /{id}
    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }


}
