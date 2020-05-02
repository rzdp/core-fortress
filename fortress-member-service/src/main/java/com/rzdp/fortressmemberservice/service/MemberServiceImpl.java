package com.rzdp.fortressmemberservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
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
//    @HystrixCommand(commandProperties = {
//        @HystrixProperty(
//                name = "execution.isolation.thread.timeoutInMilliseconds",
//                value = "12000"
//        )
//    })
    @HystrixCommand(
            commandProperties = {
                @HystrixProperty(
                        name = "execution.isolation.thread.timeoutInMilliseconds",
                        value = "10000"
                )
            },
            fallbackMethod = "getMemberBankAccountsFallback"
    )
    public MemberDto getMemberBankAccounts(String bankId) {
        sleep();
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

    private MemberDto getMemberBankAccountsFallback(String bankId) {
        return new MemberDto();
    }

    private void sleep() {
        try {
            Thread.sleep(11000);
        } catch(InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
