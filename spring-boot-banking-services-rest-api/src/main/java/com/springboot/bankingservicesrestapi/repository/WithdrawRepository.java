package com.springboot.bankingservicesrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.bankingservicesrestapi.model.Withdraw;

@Repository
public interface WithdrawRepository extends JpaRepository<Withdraw, String> {

}
