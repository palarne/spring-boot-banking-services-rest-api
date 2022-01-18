package com.springboot.bankingservicesrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.bankingservicesrestapi.model.Transfer;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, String> {

}
