package com.flower.member.controller;

import com.flower.auth.KakaoAPI;
import com.flower.member.domain.Member;
import com.flower.member.dto.response.ConnectionLinkResponse;
import com.flower.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class MemberController {

    private final MemberService memberService;

    private final KakaoAPI kakaoAPI;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping ("/login/oauth2/kakao")
    public void kakaoLogin(@RequestParam("code") String code, HttpSession httpSession){
        String accessToken = kakaoAPI.getAccessToken(code);
        HashMap<String, Object> userInfo = kakaoAPI.getUserInfo(accessToken);
        log.info("KakaoLogin Controller : "+userInfo);

        if(userInfo.get("email")!=null) {
            httpSession.setAttribute("userId", userInfo.get("email"));
            httpSession.setAttribute("access_Token", accessToken);
        }

        Member member = Member.createMember()
                .email(userInfo.get("email").toString()).name(userInfo.get("nickname").toString()).userName("kakao_"+userInfo.get("username").toString())
                .build();
        memberService.saveMember(member);
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
