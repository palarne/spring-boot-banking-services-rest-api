package com.springboot.bankingservicesrestapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bankingservicesrestapi.service.TransactionService;

@RestController
public class TransactionController {

	private final TransactionService transactionService;

	@Autowired
	public TransactionController(TransactionService transactionService) {
		super();
		this.transactionService = transactionService;
	}

}
