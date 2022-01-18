package com.springboot.bankingservicesrestapi.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bankingservicesrestapi.model.Withdraw;
import com.springboot.bankingservicesrestapi.service.WithdrawService;

@RestController
public class WithdrawController {
	
	private final WithdrawService withdrawService;

	@Autowired
	public WithdrawController(WithdrawService withdrawService) {
		super();
		this.withdrawService = withdrawService;
	}
	
	@PostMapping("/withdraw")
	@Transactional
	public ResponseEntity<Withdraw> withdrawAmount(@RequestBody Withdraw withdraw) {
		return withdrawService.withdrawAmount(withdraw);
	}

	
}
