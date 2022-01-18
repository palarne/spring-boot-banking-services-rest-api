package com.springboot.bankingservicesrestapi.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name="deposits")
@NoArgsConstructor
@AllArgsConstructor
public class Deposit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "deposit_id")
	private int id;
	
	@Column(name = "account_number")
	private String accountNumber;
	
	@Column(name = "deposit_amount")
	private BigDecimal amount;
	
	@Column(name = "deposit_timestamp")
	@CreationTimestamp
	private Timestamp timestamp;
	
	@OneToOne(mappedBy = "deposit")
	private Transaction transaction;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "transfer_id", referencedColumnName = "transfer_id")
	private Transfer transfer;

}
