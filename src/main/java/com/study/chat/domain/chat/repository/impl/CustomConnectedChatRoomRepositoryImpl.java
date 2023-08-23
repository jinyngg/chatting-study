package com.study.chat.domain.chat.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.chat.domain.chat.entity.QConnectedChatRoom;
import com.study.chat.domain.chat.repository.CustomConnectedChatRoomRepository;
import com.study.chat.domain.member.entity.QMember;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomConnectedChatRoomRepositoryImpl implements CustomConnectedChatRoomRepository {

    private final JPAQueryFactory queryFactory;

    QConnectedChatRoom qConnectedChatRoom = QConnectedChatRoom.connectedChatRoom;
    QMember qMember = QMember.member;

    @Override
    public void deleteMember(Long memberId, Long roomId) {
        queryFactory
                .delete(qConnectedChatRoom)
                .where(
                        qConnectedChatRoom.member.id.eq(memberId)
                                .and(qConnectedChatRoom.chatRoom.id.eq(roomId)))
                .execute();
    }

    @Override
    public List<String> findAllMemberChatRoom(Long roomId) {
        return queryFactory
                .select(qMember.name)
                .from(qConnectedChatRoom)
                .innerJoin(qConnectedChatRoom.member, qMember)
                .where(qConnectedChatRoom.chatRoom.id.eq(roomId))
                .fetch();
    }

    @Override
    public boolean findMember(String memberName, Long roomId) {
        Long count = queryFactory
                .select(qConnectedChatRoom.count())
                .from(qConnectedChatRoom)
                .where(
                        qConnectedChatRoom.member.name.eq(memberName)
                                .and(qConnectedChatRoom.chatRoom.id.eq(roomId))
                )
                .fetchOne();

        return count != null && count < 1;
    }
}
