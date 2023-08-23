package com.study.chat.domain.chat.dto;

import java.util.HashMap;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomDto {

    private Long id;
    private String name;
    private long count;

    private HashMap<String, String> users = new HashMap<>();

    public void createRoom(String name) {
        this.name = name;
    }

    public String getRoomName() {
        return this.name;
    }
}
