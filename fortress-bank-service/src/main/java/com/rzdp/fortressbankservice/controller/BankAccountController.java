package com.rzdp.fortressbankservice.controller;

import com.rzdp.fortressbankservice.entity.BankAccount;
import com.rzdp.fortressbankservice.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class    BankAccountController {

    private final BankAccountService bankAccountService;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/v1/bank-accounts/{bankId}")
    public List<BankAccount> getBankAccounts(@RequestHeader("x-correlation-id") String correlationId,
                                             @PathVariable String bankId) {
        return bankAccountService.getBankAccounts(correlationId, bankId);
    }

    @DeleteMapping("/v1/bank-accounts/{bankId}")
    public String deleteBankAccounts(@RequestHeader("x-correlation-id") String correlationId,
                                             @PathVariable String bankId) {
        bankAccountService.deleteBankAccounts(correlationId, bankId);
        return "Bank accounts of {} deleted successfully";
    }
}
