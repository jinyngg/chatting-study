package com.study.chat.domain.chat.repository;

import com.study.chat.domain.chat.entity.ConnectedChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectedChatRoomRepository extends JpaRepository<ConnectedChatRoom, Long> {

}
