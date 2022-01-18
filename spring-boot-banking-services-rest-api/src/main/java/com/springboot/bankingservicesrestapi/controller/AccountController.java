package com.springboot.bankingservicesrestapi.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bankingservicesrestapi.model.Account;
import com.springboot.bankingservicesrestapi.service.AccountService;

@RestController
public class AccountController {

	private final AccountService accountService;

	@Autowired
	public AccountController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}
	
	@PutMapping("/account")
	public ResponseEntity<Account> addAccount(@RequestBody Account account) {
		return accountService.addAccount(account);
	}
	
	@GetMapping("/account")
	public ResponseEntity<List<Account>> findAllAccount() {
		return accountService.findAllAccount();
	}
	
	@GetMapping("/account/{accountNumber}")
	public ResponseEntity<Optional<Account>> findOneAccount(@PathVariable String accountNumber) {
		return accountService.findOneAccount(accountNumber);
	}
	
	@GetMapping("/account/balance/{accountNumber}")
	public ResponseEntity<BigDecimal> findOneAccountBalance(@PathVariable String accountNumber) {
		return accountService.findOneAccountBalance(accountNumber);
	}
	
}
