package com.rzdp.fortressmemberservice.controller;

import com.rzdp.fortressmemberservice.dto.MemberDto;
import com.rzdp.fortressmemberservice.entity.Member;
import com.rzdp.fortressmemberservice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/v1/members")
    public List<Member> getMembers() {
        return memberService.getMembers();
    }

    @GetMapping("/v1/members/{bankId}/bank-accounts")
    public MemberDto getMemberBankAccounts(@PathVariable String bankId) {
        return memberService.getMemberBankAccounts(bankId);
    }
}
