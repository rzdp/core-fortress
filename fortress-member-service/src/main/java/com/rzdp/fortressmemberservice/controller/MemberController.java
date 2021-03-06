package com.rzdp.fortressmemberservice.controller;

import com.rzdp.fortressmemberservice.dto.MemberDto;
import com.rzdp.fortressmemberservice.entity.Member;
import com.rzdp.fortressmemberservice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/v1/lists")
    public List<Member> getMembers() {
        return memberService.getMembers();
    }

    @GetMapping("/v1/bank-accounts/{bankId}")
    public MemberDto getMemberBankAccounts(@RequestHeader("x-correlation-id") String correlationId,
                                           @PathVariable String bankId) {
        return memberService.getMemberBankAccounts(correlationId, bankId);
    }

    @DeleteMapping("/v1/{memberId}")
    public String deleteMember(@PathVariable long memberId) {
        memberService.deleteMember(memberId);
        return "Member deleted successfully";
    }
}

