package com.study.chat.domain.chat.controller;

import com.study.chat.domain.chat.dto.ChatRoomDto;
import com.study.chat.domain.chat.service.ChatRoomService;
import com.study.chat.domain.chat.service.ConnectedChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final ConnectedChatRoomService connectedChatRoomService;

    // 채팅 리스트 화면으로 요청이 들어오면 전체 채팅룸 리스트를 담아서 return
    @GetMapping("/")
    public String goChatRoom(Model model){
        model.addAttribute("list", chatRoomService.findAllChatRoom());
        return "roomlist";
    }

    // 채팅방 생성
    @PostMapping("/chat/createroom")
    public String createRoom(@RequestParam String name, RedirectAttributes rttr) {
        ChatRoomDto chatRoomDto = new ChatRoomDto();
        chatRoomDto.createRoom(name);
        name = chatRoomService.save(chatRoomDto);
        rttr.addFlashAttribute("roomName", name);
        return "redirect:/";
    }

    // 채팅방 입장 화면
    // 파라미터로 넘어오는 roomId 를 확인후 해당 roomId 를 기준으로
    // 채팅방을 찾아서 클라이언트를 chatroom 으로 보낸다.
    @GetMapping("/chat/room")
    public String roomDetail(Model model, Long roomId){
        model.addAttribute("room", chatRoomService.findById(roomId));
        return "chatroom";
    }

    @GetMapping("/chat/duplicateName")
    @ResponseBody
    public void duplicateName(@RequestParam String username, @RequestParam Long roomId){
        connectedChatRoomService.duplicate(username, roomId);
    }
}
