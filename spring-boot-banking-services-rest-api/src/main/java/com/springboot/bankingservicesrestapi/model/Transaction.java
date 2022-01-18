package com.springboot.bankingservicesrestapi.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name="transactions")
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "withdraw_id", referencedColumnName = "withdraw_id")
	private Withdraw withdraw;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "deposit_id", referencedColumnName = "deposit_id")
	private Deposit deposit;	

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "transfer_id", referencedColumnName = "transfer_id")
	private Transfer transfer;
	
	@ManyToOne
	@JoinColumn(name = "account_id", referencedColumnName = "account_id")
	private Account account;

}
