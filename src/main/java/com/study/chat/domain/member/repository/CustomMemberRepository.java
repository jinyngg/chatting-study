package com.study.chat.domain.member.repository;

import com.study.chat.domain.member.dto.MemberDto;

public interface CustomMemberRepository {

    MemberDto find (Long id);

}
