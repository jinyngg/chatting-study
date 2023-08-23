package com.study.chat.domain.member.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.chat.domain.member.dto.MemberDto;
import com.study.chat.domain.member.entity.QMember;
import com.study.chat.domain.member.repository.CustomMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomMemberRepositoryImpl implements CustomMemberRepository {

    private final JPAQueryFactory queryFactory;

    QMember qMember = QMember.member;


    @Override
    public MemberDto find(Long id) {
        return queryFactory
                .select(Projections.bean(MemberDto.class,
                        qMember.id,
                        qMember.name,
                        qMember.email
                ))
                .from(qMember)
                .where(qMember.id.eq(id))
                .fetchOne();
    }
}
