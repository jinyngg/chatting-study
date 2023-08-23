package com.study.chat.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // handshake 및 통신을 담당할 endpoint 지정
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-stomp")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }

    // 내부에서 사용할 path 지정
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 클라이언트 -> 서버, 메시지 구독 요청 (메시지 발행 URL)
        // /topic : 1:N 전파, /queue : 1:1 전파
        registry.setApplicationDestinationPrefixes("/pub");

        // 서버 -> 클라이언트, 메시지 발행 요청 (구독 요청 URL)
        // enableSimpleBroker : 해당 경로를 SimpleBroker 등록
        // SimpleBroker -> 해당하는 경로를 subscribe 하는 Client 에게 메세지 전달
        registry.enableSimpleBroker("/sub");
    }
}
