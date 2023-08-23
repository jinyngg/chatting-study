package com.study.chat.domain.member.repository;

import com.study.chat.domain.member.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>{

    Optional<Member> findByName(String name);

}