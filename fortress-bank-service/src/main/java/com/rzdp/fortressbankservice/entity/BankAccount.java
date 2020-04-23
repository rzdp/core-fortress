package com.rzdp.fortressbankservice.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "BankAccount")
@Data
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BankAccountId")
    private long bankAccountId;

    @Column(name = "BankId")
    private String bankId;

    @Column(name = "AccountNo")
    private String accountNo;

    @Column(name = "Balance")
    private BigDecimal balance;

    @Column(name = "Status")
    private String status;

    @Column(name = "StatusDate")
    private Date statusDate;
}
