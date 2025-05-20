package com.example.service;

import org.springframework.stereotype.Service;
import com.example.entity.Account;
import com.example.exception.DuplicateUsernameException;
import com.example.exception.UnsuccessfulLoginException;
import com.example.exception.UsernameRegistrationException;
import com.example.repository.AccountRepository;


@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account register(Account account) {
        if (accountRepository.findByUsername(account.getUsername()) != null){
            throw new DuplicateUsernameException("Username already Exists.");
        }
        if (account.getUsername() == null || (account.getPassword().length() < 4)){
            throw new UsernameRegistrationException("Username cannot be null, password must be at least 4 characters long.");
        }
        return accountRepository.save(account);
    }

    public Account login(String username, String password) {
        if (accountRepository.findByUsername(username) != null && (accountRepository.findByUsername(username).getPassword().equals(password))){
            return accountRepository.findByUsername(username);
        } else{
            throw new UnsuccessfulLoginException("Invalid Username or Password.");
        }
    }

}
