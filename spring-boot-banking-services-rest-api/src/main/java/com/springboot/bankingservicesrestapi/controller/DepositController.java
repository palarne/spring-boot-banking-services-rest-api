package com.springboot.bankingservicesrestapi.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bankingservicesrestapi.model.Deposit;
import com.springboot.bankingservicesrestapi.service.DepositService;

@RestController
public class DepositController {
	
	private final DepositService depositService;

	@Autowired
	public DepositController(DepositService depositService) {
		super();
		this.depositService = depositService;
	}

	@PostMapping("/deposit")
	@Transactional
	public ResponseEntity<Deposit> depositAmount(@RequestBody Deposit deposit) {
		return depositService.depositAmount(deposit);
	}
}
