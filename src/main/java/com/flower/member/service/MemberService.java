package com.flower.member.service;

import com.flower.member.domain.Member;
import com.flower.member.dto.response.ConnectionLinkResponse;
import com.flower.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;


@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    PasswordEncoder passwordEncoder;

    public Member saveMember(final Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    public ConnectionLinkResponse getConnectionLink(final Long userId) {
        final Member member = memberRepository.findById(userId)
                .orElseThrow(NoSuchElementException::new);

        return ConnectionLinkResponse.of(member);
    }

    private void validateDuplicateMember(final Member member){
        Member findMember = memberRepository.findByUserName(member.getUserName());
        if (findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

//    public Member authenticate(String userName, String password) {
//        Member member = memberRepository.findByUserName(userName);
//
//        if(!passwordEncoder.matches(password, member.getPassword())){
//            throw new BadCredentialsException("잘못된 비밀번호입니다.");
//        }
//        return member;
//    }
}
