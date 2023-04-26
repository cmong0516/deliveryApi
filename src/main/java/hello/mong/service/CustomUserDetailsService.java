package hello.mong.service;

import hello.mong.domain.entity.Member;
import hello.mong.repository.member.MemberJpaRepository;
import hello.mong.repository.member.MemberRepositoryCustom;
import hello.mong.utils.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberJpaRepository memberJpaRepository;
    private final MemberRepositoryCustom memberRepositoryCustom;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepositoryCustom.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + "회원을 찾을수 없습니다."));
        return new CustomUserDetails(member);
    }
}
