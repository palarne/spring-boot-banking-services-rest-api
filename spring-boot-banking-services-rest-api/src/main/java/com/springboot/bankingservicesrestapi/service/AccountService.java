package com.springboot.bankingservicesrestapi.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.bankingservicesrestapi.model.Account;
import com.springboot.bankingservicesrestapi.repository.AccountRepository;

@Service
public class AccountService {
	@Autowired
	AccountRepository accountRepository;
	
	public ResponseEntity<Account> addAccount(Account account) {
		Account newAccount = new Account();
		newAccount.setAccountNumber(account.getAccountNumber());
		newAccount.setBalance(account.getBalance());
		accountRepository.save(newAccount);
		return new ResponseEntity<Account>(newAccount, HttpStatus.CREATED);
	}

	public ResponseEntity<List<Account>> findAllAccount() {
		List<Account> accounts = accountRepository.findAll();
		if (accounts.isEmpty()) {
			return new ResponseEntity<List<Account>>(accounts, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Account>>(accounts, HttpStatus.OK);
		}
	}

	public ResponseEntity<Optional<Account>> findOneAccount(String accountNumber) {
		Optional<Account> oneAccount = accountRepository.findAccountByAccountNumberEquals(accountNumber);
		if (oneAccount.isEmpty()) {
			return new ResponseEntity<>(oneAccount, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Optional<Account>>(oneAccount, HttpStatus.OK);
		}
	}
	
	public ResponseEntity<BigDecimal> findOneAccountBalance(String accountNumber) {
		Optional<Account> oneAccount = accountRepository.findAccountByAccountNumberEquals(accountNumber);
		if (oneAccount.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<BigDecimal>(oneAccount.get().getBalance(), HttpStatus.OK);
		}
	}
}
