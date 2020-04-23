package com.rzdp.fortressbankservice.repository;

import com.rzdp.fortressbankservice.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    List<BankAccount> findByBankId(String bankId);
}
