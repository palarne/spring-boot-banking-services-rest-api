package com.springboot.bankingservicesrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.bankingservicesrestapi.model.Deposit;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, String>{

}
