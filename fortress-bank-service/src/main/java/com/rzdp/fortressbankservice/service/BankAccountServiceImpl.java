package com.rzdp.fortressbankservice.service;

import com.rzdp.fortressbankservice.entity.BankAccount;
import com.rzdp.fortressbankservice.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public List<BankAccount> getBankAccounts(String bankId) {
        return bankAccountRepository.findByBankId(bankId);
    }
}
