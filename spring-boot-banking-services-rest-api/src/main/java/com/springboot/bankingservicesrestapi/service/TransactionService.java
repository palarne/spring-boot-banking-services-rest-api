package com.springboot.bankingservicesrestapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.bankingservicesrestapi.model.Transaction;
import com.springboot.bankingservicesrestapi.repository.AccountRepository;
import com.springboot.bankingservicesrestapi.repository.DepositRepository;
import com.springboot.bankingservicesrestapi.repository.TransactionRepository;
import com.springboot.bankingservicesrestapi.repository.TransferRepository;
import com.springboot.bankingservicesrestapi.repository.WithdrawRepository;

@Service
public class TransactionService {
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	WithdrawRepository withdrawRepository;
	@Autowired
	DepositRepository depositRepository;
	@Autowired
	TransferRepository transferRepository;
	@Autowired
	TransactionRepository transactionRepository;

	public ResponseEntity<List<Transaction>> findAllTransaction() {
		return null;		
	}
}
