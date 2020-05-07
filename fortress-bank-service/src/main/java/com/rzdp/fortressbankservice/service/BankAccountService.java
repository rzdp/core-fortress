package com.rzdp.fortressbankservice.service;

import com.rzdp.fortressbankservice.entity.BankAccount;

import java.util.List;

public interface BankAccountService {

    List<BankAccount> getBankAccounts(String correlationId, String bankId);
}
