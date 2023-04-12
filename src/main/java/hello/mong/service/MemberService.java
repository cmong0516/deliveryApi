package hello.mong.service;

import hello.mong.domain.entity.Authority;
import hello.mong.domain.request.NewMemberRequest;
import hello.mong.domain.entity.Member;
import hello.mong.domain.request.LoginMemberRequest;
import hello.mong.domain.response.SignUpMemberResponse;
import hello.mong.repository.member.MemberJpaRepository;
import hello.mong.repository.member.MemberRepositoryCustom;
import hello.mong.utils.JwtProvider;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberJpaRepository memberJpaRepository;
    private final MemberRepositoryCustom memberRepositoryCustom;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional
    public SignUpMemberResponse signUp(NewMemberRequest newMemberRequest) {

        if (memberJpaRepository.findByEmail(newMemberRequest.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 회원 입니다.");
        }

        Member member = Member.builder()
                .email(newMemberRequest.getEmail())
                .name(newMemberRequest.getName())
                .password(passwordEncoder.encode(newMemberRequest.getPassword()))
                .phone(newMemberRequest.getPhone())
                .build();

        member.setRoles(Collections.singletonList(Authority.builder().name("ROLE_USER").build()));

        memberJpaRepository.save(member);

        return new SignUpMemberResponse(member.getEmail(),member.getName(),member.getRoles(),member.getCreateDate());
    }

    public String login(LoginMemberRequest request) {
        Member member = memberJpaRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("로그인 정보가 일치하지 않습니다."));

        if (passwordEncoder.matches(member.getPassword(), request.getPassword())) {
            throw new IllegalArgumentException("로그인 정보가 일치하지 않습니다.");
        }

        return jwtProvider.createToken(member.getEmail(),member.getRoles());
    }
}
