package com.flower.auth;

import com.flower.auth.userInfo.KakaoUserInfo;
import com.flower.auth.userInfo.OAuth2UserInfo;
import com.flower.member.Member;
import com.flower.member.MemberRepository;
import com.flower.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService{

    private final MemberRepository memberRepository;

    private final MemberService memberService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        OAuth2UserInfo oAuth2UserInfo = null;
        String provider = userRequest.getClientRegistration().getRegistrationId();

        oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());

        String providerId = oAuth2UserInfo.getProviderId();
        String username = provider+"_"+providerId;

        String uuid = UUID.randomUUID().toString().substring(0,6);
        String password = passwordEncoder.encode(uuid);

        String email = oAuth2UserInfo.getEmail();
        String name = oAuth2UserInfo.getName();
        String phoneNumber = oAuth2UserInfo.getPhoneNumber();
        Role role = Role.PATIENT;

        Member member = Member.oauth2Register()
                .username(username).email(email).password(password).name(name).phoneNumber(phoneNumber).role(role)
                .build();

        memberService.saveMember(member);

        return super.loadUser(userRequest);
    }

}
