package com.flower.member.controller;

import com.flower.member.dto.response.ConnectionLinkResponse;
import com.flower.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{userId}/connection-link")
    public ResponseEntity<ConnectionLinkResponse> getConnectionLink (
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                memberService.getConnectionLink(id)
        );
    }
}
