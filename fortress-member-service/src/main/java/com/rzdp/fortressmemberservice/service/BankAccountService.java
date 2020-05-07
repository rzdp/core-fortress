package com.rzdp.fortressmemberservice.service;

import com.rzdp.fortressmemberservice.dto.BankAccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "fortress-bank-service")
public interface BankAccountService {

    @GetMapping("/v1/bank-accounts/{bankId}")
    List<BankAccountDto> getBankAccounts(@RequestHeader("x-correlation-id") String correlationId,
                                         @PathVariable("bankId") String bankId);
}
