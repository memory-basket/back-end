package com.flower.member.domain;

import com.flower.auth.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "member")
@Getter @Setter
@ToString
public class Member {

    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    private String name;

    private String phoneNumber;

    private String providerId;

    private String connectionUrl;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Member() {
        generateLink();
    }

    @Builder(builderClassName = "OAuth2Register", builderMethodName = "oauth2Register")
    public Member(String username, String email, String password, String name, String phoneNumber, Role role, String providerId) {
        this.connectionUrl = generateLink();
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.providerId = providerId;
    }

    private String generateLink() {
        return UUID.randomUUID().toString();
    }
}
