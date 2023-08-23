package com.study.chat.domain.member.service;

import com.study.chat.domain.member.dto.MemberDto;

public interface MemberService {

    MemberDto find(Long id);
}
