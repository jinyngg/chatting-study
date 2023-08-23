package com.study.chat.domain.chat.service;

import com.study.chat.domain.chat.dto.ChatRoomDto;
import com.study.chat.domain.chat.entity.ChatRoom;
import com.study.chat.domain.chat.entity.ConnectedChatRoom;
import com.study.chat.domain.chat.repository.ChatRoomRepository;
import com.study.chat.domain.chat.repository.ConnectedChatRoomRepository;
import com.study.chat.domain.chat.repository.CustomChatRoomRepository;
import com.study.chat.domain.chat.repository.CustomConnectedChatRoomRepository;
import com.study.chat.domain.member.entity.Member;
import com.study.chat.domain.member.repository.MemberRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final MemberRepository memberRepository;
    private final ConnectedChatRoomRepository connectedChatRoomRepository;

    private final CustomChatRoomRepository customChatRoomRepository;
    private final CustomConnectedChatRoomRepository customConnectedChatRoomRepository;

    public List<ChatRoomDto> findAllChatRoom(){
        return customChatRoomRepository.findAll();
    }

    public ChatRoomDto findById(Long roomId) {
        return customChatRoomRepository.findById(roomId);
    }

    public String save(ChatRoomDto chatRoomDto) {
        ChatRoom chatRoom = new ChatRoom(chatRoomDto);
        chatRoomRepository.save(chatRoom);

        return chatRoom.getName();
    }

    public Long addMemberToChatRoom(Long roomId, String memberName) {
        ChatRoom chatRoom = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("ROOM_UNKNOWN"));

        Member member = new Member();
        member.createName(memberName);

        memberRepository.save(member);

        ConnectedChatRoom chatRoomMember = new ConnectedChatRoom();
        chatRoomMember.createChatRoom(chatRoom);
        chatRoomMember.createMember(member);

        connectedChatRoomRepository.save(chatRoomMember);

        chatRoom.plusMember();

        return member.getId();
    }

    public List<String> findAllMember(Long roomId){
        return customConnectedChatRoomRepository.findAllMemberChatRoom(roomId);
    }

    public void plusMember(Long roomId) {
        ChatRoom chatRoom = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("ROOM_UNKNOWN"));
        chatRoom.plusMember();
    }

    public void minusMember(Long roomId) {
        ChatRoom chatRoom = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("ROOM_UNKNOWN"));
        chatRoom.minusMember();
    }
}
