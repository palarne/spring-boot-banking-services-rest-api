package com.springboot.bankingservicesrestapi.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.bankingservicesrestapi.model.Account;
import com.springboot.bankingservicesrestapi.model.Transaction;
import com.springboot.bankingservicesrestapi.model.Withdraw;
import com.springboot.bankingservicesrestapi.repository.AccountRepository;
import com.springboot.bankingservicesrestapi.repository.TransactionRepository;
import com.springboot.bankingservicesrestapi.repository.WithdrawRepository;

@Service
public class WithdrawService {
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	TransactionRepository transactionRepository;
	@Autowired
	WithdrawRepository withdrawRepository;

	@Transactional
	public ResponseEntity<Withdraw> withdrawAmount(Withdraw withdraw) {
		if (!accountIsPresent(withdraw)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else if (!balanceIsPresent(withdraw)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		assignToWithdraw(withdraw);
		assignToTransaction(withdraw);
		return new ResponseEntity<Withdraw>(withdraw, HttpStatus.OK);
	}

	public void assignToWithdraw(Withdraw withdraw) {
		Withdraw newWithdraw = new Withdraw();
		newWithdraw.setAccountNumber(withdraw.getAccountNumber());
		newWithdraw.setAmount(withdraw.getAmount());
		updateBalance(newWithdraw);
		withdrawRepository.save(withdraw);
	}

	public void assignToTransaction(Withdraw withdraw) {
		Transaction newTransaction = new Transaction();
		newTransaction.setWithdraw(withdraw);
		transactionRepository.save(newTransaction);
	}

	public Boolean accountIsPresent(Withdraw withdraw) {
		Optional<Account> account = accountRepository.findAccountByAccountNumberEquals(withdraw.getAccountNumber());
		if (account.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public Boolean balanceIsPresent(Withdraw withdraw) {
		Optional<Account> account = accountRepository.findAccountByAccountNumberEquals(withdraw.getAccountNumber());
		if (account.get().getBalance().compareTo(withdraw.getAmount()) < 0) {
			return false;
		} else {
			return true;
		}
	}

	public void updateBalance(Withdraw withdraw) {
		Optional<Account> account = accountRepository.findAccountByAccountNumberEquals(withdraw.getAccountNumber());
		account.get().setBalance(account.get().getBalance().subtract(withdraw.getAmount()));
	}

}
