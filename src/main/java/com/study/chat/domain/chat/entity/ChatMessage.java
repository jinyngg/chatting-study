package com.study.chat.domain.chat.entity;

import com.study.chat.common.entity.BaseEntity;
import com.study.chat.domain.chat.type.MessageType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ChatMessage extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private MessageType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id")
//    private Member sender;

    private String sender;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;
}
