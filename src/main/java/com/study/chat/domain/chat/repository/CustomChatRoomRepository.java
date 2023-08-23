package com.study.chat.domain.chat.repository;

import com.study.chat.domain.chat.dto.ChatRoomDto;
import java.util.List;

public interface CustomChatRoomRepository {

    List<ChatRoomDto> findAll();
    ChatRoomDto	findById(Long id);
}
