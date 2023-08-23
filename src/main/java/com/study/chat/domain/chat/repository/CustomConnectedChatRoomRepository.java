package com.study.chat.domain.chat.repository;

import java.util.List;

public interface CustomConnectedChatRoomRepository {

    void deleteMember(Long memberId, Long roomId);
    List<String> findAllMemberChatRoom(Long roomId);
    boolean findMember(String memberName, Long roomId);
}
