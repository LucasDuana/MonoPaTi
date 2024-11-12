package org.example.usermicroservice.service;

import org.example.usermicroservice.dtos.AccountDTO;
import org.example.usermicroservice.model.Account;
import org.example.usermicroservice.repositories.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<AccountDTO> getAllAccounts() {
        return accountRepository.findAll().stream().map(account -> modelMapper.map(account, AccountDTO.class)).collect(Collectors.toList());
    }

    public AccountDTO getAccountById(Long id) {
        return modelMapper.map(accountRepository.findById(id).get(), AccountDTO.class);
    }

    public AccountDTO chargeAccount(Long id, Double amount) {
        Account account = accountRepository.findById(id).get();
        account.setBalance(account.getBalance() - amount);
        return modelMapper.map(accountRepository.save(account), AccountDTO.class);
    }


    public AccountDTO setAccountEnabled(Long id) {
        Account account = accountRepository.findById(id).get();
        account.setStatus("enabled");
        return modelMapper.map(accountRepository.save(account), AccountDTO.class);
    }

    public AccountDTO setAccountDisabled(Long id) {
        Account account = accountRepository.findById(id).get();
        account.setStatus("disabled");
        return modelMapper.map(accountRepository.save(account), AccountDTO.class);
    }

    public AccountDTO createAccount(AccountDTO accountDTO) {
        return modelMapper.map(accountRepository.save(modelMapper.map(accountDTO, Account.class)), AccountDTO.class);
    }

    public AccountDTO updateAccount(Long id, AccountDTO accountDTO) {
        Account account = accountRepository.findById(id).get();
        account.setBalance(accountDTO.getBalance());
        account.setName(accountDTO.getName());
        account.setCreationDate(accountDTO.getCreationDate());
        return modelMapper.map(accountRepository.save(account), AccountDTO.class);
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

}