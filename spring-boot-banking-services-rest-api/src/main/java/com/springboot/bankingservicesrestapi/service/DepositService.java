package com.springboot.bankingservicesrestapi.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.bankingservicesrestapi.model.Account;
import com.springboot.bankingservicesrestapi.model.Deposit;
import com.springboot.bankingservicesrestapi.model.Transaction;
import com.springboot.bankingservicesrestapi.repository.AccountRepository;
import com.springboot.bankingservicesrestapi.repository.DepositRepository;
import com.springboot.bankingservicesrestapi.repository.TransactionRepository;

@Service
public class DepositService {
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	TransactionRepository transactionRepository;
	@Autowired
	DepositRepository depositRepository;
	
	@Transactional
	public ResponseEntity<Deposit> depositAmount(Deposit deposit) {
		if (!accountIsPresent(deposit)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			assignToDeposit(deposit);
			assignToTransaction(deposit);
			return new ResponseEntity<Deposit>(deposit, HttpStatus.OK);
		}		
	}
	
	public void assignToDeposit(Deposit deposit) {
		Deposit newDeposit = new Deposit();
		newDeposit.setAccountNumber(deposit.getAccountNumber());
		newDeposit.setAmount(deposit.getAmount());
		updateBalance(newDeposit);
		depositRepository.save(deposit);
	}
	
	public void assignToTransaction(Deposit deposit) {
		Transaction newTransaction = new Transaction();
		newTransaction.setDeposit(deposit);
		transactionRepository.save(newTransaction);
	}
	
	public Boolean accountIsPresent(Deposit deposit) {
		Optional<Account> account = accountRepository.findAccountByAccountNumberEquals(deposit.getAccountNumber());
		if (account.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	public void updateBalance(Deposit deposit) {
		Optional<Account> account = accountRepository.findAccountByAccountNumberEquals(deposit.getAccountNumber());
		account.get().setBalance(account.get().getBalance().add(deposit.getAmount()));
	}

}
