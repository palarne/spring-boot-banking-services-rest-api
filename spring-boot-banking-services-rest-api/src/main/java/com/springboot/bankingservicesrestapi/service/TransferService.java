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
import com.springboot.bankingservicesrestapi.model.Transfer;
import com.springboot.bankingservicesrestapi.model.Withdraw;
import com.springboot.bankingservicesrestapi.repository.AccountRepository;
import com.springboot.bankingservicesrestapi.repository.DepositRepository;
import com.springboot.bankingservicesrestapi.repository.TransactionRepository;
import com.springboot.bankingservicesrestapi.repository.TransferRepository;
import com.springboot.bankingservicesrestapi.repository.WithdrawRepository;

@Service
public class TransferService {
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	WithdrawRepository withdrawRepository;
	@Autowired
	DepositRepository depositRepository;
	@Autowired
	TransactionRepository transactionRepository;
	@Autowired
	TransferRepository transferRepository;
	
	@Transactional
	public ResponseEntity<Transfer> transferAmount(Transfer transfer) {
		if (!verifyTransfer(transfer)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			assignToTransfer(transfer);
			assignToWithdraw(transfer);
			assignToDeposit(transfer);
			assignToTransaction(transfer);
			return new ResponseEntity<Transfer>(transfer, HttpStatus.OK);	
		}		
	}
	
	public void assignToTransfer(Transfer transfer) {
		Transfer newTransfer = new Transfer();
		newTransfer.setAccountNumberFrom(transfer.getAccountNumberFrom());
		newTransfer.setAccountNumberTo(transfer.getAccountNumberTo());
		newTransfer.setAmount(transfer.getAmount());
		transferRepository.save(transfer);
	}
	
	public void assignToWithdraw(Transfer transfer) {
		Withdraw newWithdraw = new Withdraw();
		newWithdraw.setAccountNumber(transfer.getAccountNumberFrom());
		newWithdraw.setAmount(transfer.getAmount());
		newWithdraw.setTransfer(transfer);
		updateWithdrawBalance(newWithdraw.getTransfer());
		withdrawRepository.save(newWithdraw);
	}
	
	public void assignToDeposit(Transfer transfer) {
		Deposit newDeposit = new Deposit();
		newDeposit.setAccountNumber(transfer.getAccountNumberTo());
		newDeposit.setAmount(transfer.getAmount());
		newDeposit.setTransfer(transfer);
		updateDepositBalance(newDeposit.getTransfer());
		depositRepository.save(newDeposit);
	}
	
	public void assignToTransaction(Transfer transfer) {
		Transaction newTransaction = new Transaction();
		newTransaction.setTransfer(transfer);
		transactionRepository.save(newTransaction);
	}
	
	public boolean verifyTransfer(Transfer transfer) {
		if (!(checkAccountFrom(transfer) && checkAccountFromBalance(transfer) && checkAccountTo(transfer))) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean checkAccountFrom(Transfer transfer) {
		Optional<Account> account = accountRepository.findAccountByAccountNumberEquals(transfer.getAccountNumberFrom());
		if (account.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean checkAccountFromBalance(Transfer transfer) {
		Optional<Account> account = accountRepository.findAccountByAccountNumberEquals(transfer.getAccountNumberFrom());
		if (account.get().getBalance().compareTo(transfer.getAmount()) < 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean checkAccountTo(Transfer transfer) {
		Optional<Account> account = accountRepository.findAccountByAccountNumberEquals(transfer.getAccountNumberTo());
		if (account.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	public void updateWithdrawBalance(Transfer transfer) {
		Optional<Account> account = accountRepository.findAccountByAccountNumberEquals(transfer.getAccountNumberFrom());
		account.get().setBalance(account.get().getBalance().subtract(transfer.getAmount()));
	}
	
	public void updateDepositBalance(Transfer transfer) {
		Optional<Account> account = accountRepository.findAccountByAccountNumberEquals(transfer.getAccountNumberTo());
		account.get().setBalance(account.get().getBalance().add(transfer.getAmount()));
	}

	
}
