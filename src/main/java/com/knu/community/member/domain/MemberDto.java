package com.knu.community.member.domain;

import lombok.Data;

@Data
public class MemberDto {

    private String email;
    private String nickname;

    public MemberDto(Member member) {
        this.email = member.getEmail();
        this.nickname = member.getNickname();
    }
}
