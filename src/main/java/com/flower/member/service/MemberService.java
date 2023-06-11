package com.flower.member.service;

import com.flower.auth.KakaoAPI;
import com.flower.common.util.JwtUtil;
import com.flower.member.constant.Role;
import com.flower.member.domain.Member;
import com.flower.member.dto.MemberDto;
import com.flower.member.dto.response.ConnectionLinkResponse;
import com.flower.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.NoSuchElementException;


@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final KakaoAPI kakaoAPI;

    private final MemberRepository memberRepository;

    private final JwtUtil jwtUtil;

    PasswordEncoder passwordEncoder;

    public MemberDto kakaoLogin(String accessToken) throws Exception {
        HashMap<String, Object> userInfo = kakaoAPI.getUserInfo(accessToken);
        log.info("KakaoLogin Controller : "+userInfo);

        String username = "kakao_"+userInfo.get("username").toString();

        Member member = Member.createMember()
                .email(userInfo.get("email").toString()).name(userInfo.get("nickname").toString()).userName(username)
                .build();

        Member savedMember = findByUserKakaoIdentifier(member);
        if (savedMember == null){
            signUp(member);
            savedMember = findByUserKakaoIdentifier(member);
        }
        String token = jwtUtil.createToken(savedMember.getId(), savedMember.getUserName());
        return new MemberDto(token, savedMember.getUserName());
    }

    public ConnectionLinkResponse getConnectionLink(final Long userId) {
        final Member member = memberRepository.findById(userId)
                .orElseThrow(NoSuchElementException::new);

        return ConnectionLinkResponse.of(member);
    }

    private Member findByUserKakaoIdentifier(final Member member){
        Member findMember = memberRepository.findByUserName(member.getUserName());
        if (findMember == null){
            return null;
        }
        return findMember;
    }

    public void signUp(Member member) throws Exception {
        try {
            memberRepository.save(member).getId();
        } catch (Exception e){
            throw new Exception("회원가입에 실패했습니다.");
        }
    }

    public MemberDto updateRole(String username, Role role) {
        Member member = memberRepository.findByUserName(username);
        member.setRole(role);
        memberRepository.flush();
        return new MemberDto(member.getUserName(), member.getEmail(), member.getName(), member.getPhoneNumber(), member.getRole());
    }

}
