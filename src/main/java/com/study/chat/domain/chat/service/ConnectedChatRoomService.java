package com.study.chat.domain.chat.service;

import com.study.chat.domain.chat.repository.ConnectedChatRoomRepository;
import com.study.chat.domain.chat.repository.CustomConnectedChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConnectedChatRoomService {

    private final ChatRoomService chatRoomService;
    private final ConnectedChatRoomRepository connectedChatRoomRepository;
    private final CustomConnectedChatRoomRepository customConnectedChatRoomRepository;

    public void deleteMember(Long memberId, Long roomId) {
        customConnectedChatRoomRepository.deleteMember(memberId, roomId);
    }

    public void duplicate(String memberName, Long roomId) {
        boolean check = customConnectedChatRoomRepository.findMember(memberName, roomId);
        if(check) {
            chatRoomService.addMemberToChatRoom(roomId, memberName);
        }
        else {
            throw new RuntimeException("ROOM_UNKNOWN");
        }
    }

}
