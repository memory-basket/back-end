package com.flower.session.controller;

import com.flower.common.util.JwtUtil;
import com.flower.member.domain.Member;
import com.flower.member.service.MemberService;
import com.flower.session.dto.SessionRequestDto;
import com.flower.session.dto.SessionResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/session")
public class SessionController {

    @Autowired
    MemberService memberService;

    @Autowired
    JwtUtil jwtUtil;

//    @PostMapping("/token")
//    public ResponseEntity<SessionResponseDto> create(@RequestBody SessionRequestDto resource) throws URISyntaxException {
//        Member member = memberService.authenticate(resource.getEmail(), resource.getPassword());
//        String accessToken = jwtUtil.createToken(member.getId(), member.getName());
//        String url = "/session";
//        return ResponseEntity.created(new URI(url)).body(SessionResponseDto
//                .builder()
//                .accessToken(accessToken)
//                .build());
//    }

}
