package com.springboot.bankingservicesrestapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.bankingservicesrestapi.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String>{
	Optional<Account> findAccountByAccountNumberEquals(String accountNumber);
}
