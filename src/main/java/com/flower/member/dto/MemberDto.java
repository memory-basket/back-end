package com.flower.member.dto;

import com.flower.member.constant.Role;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberDto {

    private String token;
    private String username;

    public MemberDto(String token, String username) {
        this.token = token;
        this.username = username;
    }
}
