package com.study.chat.domain.chat.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.chat.domain.chat.dto.ChatRoomDto;
import com.study.chat.domain.chat.entity.QChatRoom;
import com.study.chat.domain.chat.repository.CustomChatRoomRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomChatRoomRepositoryImpl implements CustomChatRoomRepository {

    private final JPAQueryFactory queryFactory;

    QChatRoom qChatRoom = QChatRoom.chatRoom;

    @Override
    public List<ChatRoomDto> findAll() {

        return queryFactory
                .select(Projections.bean(ChatRoomDto.class,
                        qChatRoom.id,
                        qChatRoom.name,
                        qChatRoom.count
                ))
                .from(qChatRoom)
                .orderBy(qChatRoom.createdAt.desc())
                .fetch();
    }

    @Override
    public ChatRoomDto findById(Long id) {
        return queryFactory
                .select(Projections.bean(ChatRoomDto.class,
                        qChatRoom.id,
                        qChatRoom.name,
                        qChatRoom.count
                ))
                .from(qChatRoom)
                .where(qChatRoom.id.eq(id))
                .fetchOne();
    }
}
