package com.rzdp.fortressmemberservice.repository;

import com.rzdp.fortressmemberservice.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByBankId(String bankId);
}
