package com.study.chat.domain.chat.dto;

import com.study.chat.domain.chat.type.MessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDto {

    private Long id;
    private Long roomId;
    private MessageType messageType;
    private String sender;
    private String message;

}
