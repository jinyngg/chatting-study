package com.study.chat.domain.member.service.impl;

import com.study.chat.domain.member.dto.MemberDto;
import com.study.chat.domain.member.repository.CustomMemberRepository;
import com.study.chat.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final CustomMemberRepository customMemberRepository;

    @Override
    public MemberDto find(Long id) {
        return customMemberRepository.find(id);
    }
}
