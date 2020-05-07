package com.rzdp.fortressbankservice.service;

import com.rzdp.fortressbankservice.entity.BankAccount;
import com.rzdp.fortressbankservice.repository.BankAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private static final Logger log = LoggerFactory.getLogger(BankAccountServiceImpl.class);
    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public List<BankAccount> getBankAccounts(String correlationId, String bankId) {
        log.debug("Getting bank account details for: [{}]", correlationId);
        return bankAccountRepository.findByBankId(bankId);
    }
}
