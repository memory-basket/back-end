package com.flower.member.controller;

import com.flower.auth.KakaoAPI;
import com.flower.member.domain.Member;
import com.flower.member.dto.MemberDto;
import com.flower.member.dto.response.ConnectionLinkResponse;
import com.flower.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class MemberController {

    private final MemberService memberService;

    private final KakaoAPI kakaoAPI;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/login/kakao")
    public ResponseEntity<MemberDto> kakaoCallback(@RequestParam("code") String code) throws Exception {
        String accessToken = kakaoAPI.getAccessToken(code);
        return ResponseEntity.ok(memberService.kakaoLogin(accessToken));
    }

    @GetMapping("/{userId}/connection-link")
    public ResponseEntity<ConnectionLinkResponse> getConnectionLink (
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                memberService.getConnectionLink(id)
        );
    }


}
