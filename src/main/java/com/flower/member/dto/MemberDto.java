package com.flower.member.dto;

import com.flower.member.constant.Role;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberDto {

    private String token;
    private String username;

    private String email;

    private String name;

    private String phoneNumber;

    private Role role;

    public MemberDto(String token, String username) {
        this.token = token;
        this.username = username;
    }

    public MemberDto(String username, String email, String name, String phoneNumber, Role role) {
        this.username = username;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }


}
