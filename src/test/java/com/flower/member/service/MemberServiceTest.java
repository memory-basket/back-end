package com.flower.member.service;

import com.flower.member.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Member OAuth2Register(){
        String username = "kakao_123";
        String email = "hosu0125@naver.com";
        String name = "장효선";
        String phoneNumber = "01083438285";

        return Member.createMember()
                .userName(username).email(email).name(name).phoneNumber(phoneNumber)
                .build();
    }

    @Test
    @DisplayName("회원가입 테스트")
    public void saveMember() {
        Member member = OAuth2Register();
//        Member savedMember = memberService.saveMember(member);

//        assertEquals(member.getUserName(), savedMember.getUserName());
//        assertEquals(member.getEmail(), savedMember.getEmail());
//        assertEquals(member.getName(), savedMember.getName());
//        assertEquals(member.getPhoneNumber(), savedMember.getPhoneNumber());
//        assertEquals(member.getRole(), savedMember.getRole());
    }

//    @Test
//    @DisplayName("중복 회원 가입 테스트")
//    public void saveDuplicateMemberTest() {
//        Member member1 = OAuth2Register();
//        Member member2 = OAuth2Register();
////        memberService.saveMember(member1);
//
//        Throwable e = assertThrows(IllegalStateException.class, () -> {
////            memberService.saveMember(member2);
//        });
//        assertEquals("이미 가입된 회원입니다.", e.getMessage());
//    }
}