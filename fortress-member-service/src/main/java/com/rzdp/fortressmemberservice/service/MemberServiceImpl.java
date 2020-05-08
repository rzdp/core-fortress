package com.rzdp.fortressmemberservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.rzdp.fortressmemberservice.dto.BankAccountDto;
import com.rzdp.fortressmemberservice.dto.MemberDto;
import com.rzdp.fortressmemberservice.entity.Member;
import com.rzdp.fortressmemberservice.filters.UserContext;
import com.rzdp.fortressmemberservice.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);
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
                            value = "60000"
                    )
            },
            fallbackMethod = "getMemberBankAccountsFallback"
    )
    public MemberDto getMemberBankAccounts(String correlationId, String bankId) {
        sleep();
        log.debug("Requesting bank account details for: [{}]", correlationId);

        Member member = memberRepository.findByBankId(bankId);
        if (member == null) {
            throw new EntityNotFoundException("Member not found");
        }
        MemberDto memberDto = new MemberDto();
        List<BankAccountDto> bankAccounts = bankAccountService.getBankAccounts(correlationId, bankId);
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

    @Override
    public void deleteMember(long memberId) {
        memberRepository.deleteById(memberId);
    }

    private MemberDto getMemberBankAccountsFallback(String correlationId, String bankId) {
        return new MemberDto();
    }

    private void sleep() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
