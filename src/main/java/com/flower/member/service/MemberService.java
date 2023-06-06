package com.flower.member.service;

import com.flower.member.domain.Member;
import com.flower.member.dto.response.ConnectionLinkResponse;
import com.flower.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
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
        Member findMember = memberRepository.findByUsername(member.getUsername());
        if (findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
}
