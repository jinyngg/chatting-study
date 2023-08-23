package com.study.chat.domain.chat.controller;

import com.study.chat.domain.chat.dto.ChatMessageDto;
import com.study.chat.domain.chat.service.ChatRoomService;
import com.study.chat.domain.chat.service.ConnectedChatRoomService;
import com.study.chat.domain.chat.type.MessageType;
import com.study.chat.domain.member.dto.MemberDto;
import com.study.chat.domain.member.service.MemberService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Controller
@RequiredArgsConstructor
public class ChatMessageController {

    private final SimpMessageSendingOperations template;
    private final ChatRoomService chatRoomService;
    private final ConnectedChatRoomService connectedChatRoomService;
    private final MemberService memberService;

    // MessageMapping 을 통해 webSocket 로 들어오는 메시지를 발신 처리한다.
    // 이때 클라이언트에서는 /pub/chat/message 로 요청하게 되고 이것을 controller 가 받아서 처리한다.
    // 처리가 완료되면 /sub/chat/room/roomId 로 메시지가 전송된다.
    @MessageMapping("/chat/enterUser")
    public void enterUser(@Payload ChatMessageDto chat, SimpMessageHeaderAccessor headerAccessor) {

        // 채팅방 유저+1
        chatRoomService.plusMember(chat.getId());

        // 채팅방에 유저 추가 및 UserUUID 반환
        Long memberId = chatRoomService.addMemberToChatRoom(chat.getRoomId(), chat.getSender());

        // 반환 결과를 socket session 에 userUUID 로 저장
        headerAccessor.getSessionAttributes().put("memberId", memberId);
        headerAccessor.getSessionAttributes().put("roomId", chat.getRoomId());

        chat.setMessage(chat.getSender() + " 님 입장!!");
        template.convertAndSend("/sub/chat/room/" + chat.getRoomId(), chat);

    }

    // 해당 유저
    @MessageMapping("/chat/sendMessage")
    public void sendMessage(@Payload ChatMessageDto chat) {
        chat.setMessage(chat.getMessage());
        template.convertAndSend("/sub/chat/room/" + chat.getRoomId(), chat);
    }


    // 유저 퇴장 시에는 EventListener 을 통해서 유저 퇴장을 확인
    @EventListener
    public void webSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        // stomp 세션에 있던 uuid 와 roomId 를 확인해서 채팅방 유저 리스트와 room 에서 해당 유저를 삭제
        Long memberId = (Long) headerAccessor.getSessionAttributes().get("memberId");
        Long roomId = (Long) headerAccessor.getSessionAttributes().get("roomId");

        // 채팅방 유저 -1
        chatRoomService.minusMember(roomId);

        // 채팅방 유저 리스트에서 UUID 유저 닉네임 조회 및 리스트에서 유저 삭제
        MemberDto member = memberService.find(memberId);
        connectedChatRoomService.deleteMember(memberId, roomId);

        String username = member.getName();

        if (username != null) {
            // builder 어노테이션 활용
            ChatMessageDto chat = ChatMessageDto.builder()
                    .messageType(MessageType.LEAVE)
                    .sender(username)
                    .message(username + " 님 퇴장!!")
                    .build();

            template.convertAndSend("/sub/chat/room/" + roomId, chat);
        }
    }

    // 채팅에 참여한 유저 리스트 반환
    @GetMapping("/chat/userlist")
    @ResponseBody
    public List<String> userList(Long roomId) {

        return chatRoomService.findAllMember(roomId);
    }
}
