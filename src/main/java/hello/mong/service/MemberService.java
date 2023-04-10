package hello.mong.service;

import hello.mong.domain.Role;
import hello.mong.domain.request.CreateMemberForm;
import hello.mong.domain.entity.Member;
import hello.mong.domain.response.SignUpMemberResponse;
import hello.mong.repository.MemberJpaRepository;
import hello.mong.repository.MemberRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberJpaRepository memberJpaRepository;
    private final MemberRepositoryCustom memberRepositoryCustom;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public SignUpMemberResponse signUp(CreateMemberForm createMemberForm) {

        if (memberJpaRepository.findByEmail(createMemberForm.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 회원 입니다.");
        }

        Member member = Member.builder()
                .email(createMemberForm.getEmail())
                .name(createMemberForm.getName())
                .password(passwordEncoder.encode(createMemberForm.getPassword()))
                .role(Role.USER)
                .build();

        memberJpaRepository.save(member);

        return new SignUpMemberResponse(member.getEmail(), member.getName(),member.getRole(),member.getCreateDate());
    }
}
