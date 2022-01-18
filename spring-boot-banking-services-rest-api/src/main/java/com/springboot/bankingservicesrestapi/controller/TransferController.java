package com.springboot.bankingservicesrestapi.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bankingservicesrestapi.model.Transfer;
import com.springboot.bankingservicesrestapi.service.TransferService;

@RestController
public class TransferController {
	
	private final TransferService transferService;

	@Autowired
	public TransferController(TransferService transferService) {
		super();
		this.transferService = transferService;
	}
	
	@PostMapping("/transfer")
	@Transactional
	public ResponseEntity<Transfer> transferAmount(@RequestBody Transfer transfer) {
		return transferService.transferAmount(transfer);
		
	}


}
