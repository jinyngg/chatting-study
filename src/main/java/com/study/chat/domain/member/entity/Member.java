package com.study.chat.domain.member.entity;

import com.study.chat.common.entity.BaseEntity;
import com.study.chat.domain.chat.entity.ConnectedChatRoom;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "member")
    private List<ConnectedChatRoom> connectedChatRooms = new ArrayList<>();

    public void createName(String name) {
        this.name = name;
    }
}
