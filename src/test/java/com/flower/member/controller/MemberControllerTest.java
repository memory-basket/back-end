package com.flower.member.controller;

import com.flower.member.constant.Role;
import com.flower.member.domain.Member;
import com.flower.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class MemberControllerTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("역할 부여 테스트")
    @WithMockUser(username = "hyoseon", password = "12345", roles = "")
    public void setRole() {
        Member newMember = new Member();
        String role = "patient";
        Role selectedRole = null;
        if (role == "patient"){
            selectedRole = Role.PATIENT;
        } else if (role == "protector") {
            selectedRole = Role.PROTECTOR;
        }
        newMember.setUserName("kakao_0125");
        newMember.setRole(selectedRole);
        memberRepository.save(newMember);

        em.flush();
        em.clear();

        Member member = memberRepository.findByUserName(newMember.getUserName());
        System.out.println("username: "+member.getUserName());
        System.out.println("role: "+member.getRole().toString());
    }

}