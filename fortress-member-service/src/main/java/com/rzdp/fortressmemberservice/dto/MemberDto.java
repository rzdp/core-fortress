package com.rzdp.fortressmemberservice.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Data
public class MemberDto {

    private String bankId;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date birthDate;
    private boolean active;
    private List<BankAccountDto> bankAccounts;
}
