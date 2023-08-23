package com.study.chat.domain.chat.entity;

import com.study.chat.common.entity.BaseEntity;
import com.study.chat.domain.chat.dto.ChatRoomDto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ChatRoom extends BaseEntity implements Serializable {

    private String name;
    private long count;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConnectedChatRoom> connectedChatRooms = new ArrayList<>();

    @OneToMany(mappedBy = "chatRoom", fetch = FetchType.LAZY)
    private List<ChatMessage> messages = new ArrayList<>();

    public void minusMember() {
        this.count--;
    }

    public void plusMember() {
        this.count++;
    }

    public ChatRoom(ChatRoomDto chatRoomDto) {
        this.id = chatRoomDto.getId();
        this.name = chatRoomDto.getName();
        this.count = chatRoomDto.getCount();
    }
}
