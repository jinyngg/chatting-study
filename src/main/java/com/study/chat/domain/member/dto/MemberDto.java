package com.study.chat.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    private Long id;
    private String email;
    private String name;
    private String password;


}
