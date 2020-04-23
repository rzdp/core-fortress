package com.rzdp.fortressmemberservice.service;

import com.rzdp.fortressmemberservice.dto.BankAccountDto;
import com.rzdp.fortressmemberservice.dto.MemberDto;
import com.rzdp.fortressmemberservice.entity.Member;
import com.rzdp.fortressmemberservice.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final BankAccountService bankAccountService;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, BankAccountService bankAccountService) {
        this.memberRepository = memberRepository;
        this.bankAccountService = bankAccountService;
    }

    @Override
    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    @Override
    public MemberDto getMemberBankAccounts(String bankId) {
        Member member = memberRepository.findByBankId(bankId);
        if (member == null) {
            throw new EntityNotFoundException("Member not found");
        }
        MemberDto memberDto = new MemberDto();
        List<BankAccountDto> bankAccounts = bankAccountService.getBankAccounts(bankId);
        if (bankAccounts.isEmpty()) {
            throw new EntityNotFoundException("Accounts not found");
        }
        memberDto.setBankId(member.getBankId());
        memberDto.setFirstName(member.getFirstName());
        memberDto.setMiddleName(member.getMiddleName());
        memberDto.setLastName(member.getLastName());
        memberDto.setBirthDate(member.getBirthDate());
        memberDto.setActive(member.isActive());
        memberDto.setBankAccounts(bankAccounts);
        return memberDto;
    }
}
