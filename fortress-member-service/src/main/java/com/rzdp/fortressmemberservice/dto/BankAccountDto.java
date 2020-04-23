package com.rzdp.fortressmemberservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BankAccountDto {

    private long bankAccountId;
    private String accountNo;
    private BigDecimal balance;
    private String status;
    private Date statusDate;
}
