package com.flower.member.domain;

import com.flower.member.constant.Role;
import com.flower.team.domain.Team;
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
    private String userName;

    private String email;

    private String name;

    private String phoneNumber;

    private String connectionUrl;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;

    public Member() {
        generateLink();
    }

    @Builder(builderClassName = "createMember", builderMethodName = "createMember")
    public Member(String userName, String email, String name, String phoneNumber, Role role, Team team) {
        this.connectionUrl = generateLink();
        this.userName = userName;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.team = team;
    }

    private String generateLink() {
        return UUID.randomUUID().toString();
    }
}
