package com.rzdp.fortressmemberservice.service;

import com.rzdp.fortressmemberservice.dto.MemberDto;
import com.rzdp.fortressmemberservice.entity.Member;

import java.util.List;

public interface MemberService {

    List<Member> getMembers();

    MemberDto getMemberBankAccounts(String bankId);
}
