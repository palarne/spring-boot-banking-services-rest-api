package com.springboot.bankingservicesrestapi.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name="transfers")
@NoArgsConstructor
@AllArgsConstructor
public class Transfer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transfer_id")
	private int id;
	
	@Column(name = "account_number_from")
	private String accountNumberFrom;
	
	@Column(name = "account_number_to")
	private String accountNumberTo;
	
	@Column(name = "transfer_amount")
	private BigDecimal amount;
	
	@Column(name = "transfer_timestamp")
	@CreationTimestamp
	private Timestamp timestamp;
	
	@OneToOne(mappedBy = "transfer")
	private Transaction transaction;
	
	@OneToOne(mappedBy = "transfer")
	private Deposit deposit;

	@OneToOne(mappedBy = "transfer")
	private Withdraw withdraw;
}
