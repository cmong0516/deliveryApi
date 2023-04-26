package hello.mong.repository.member;

import hello.mong.domain.entity.Member;
import hello.mong.domain.response.member.AllMemberResponse;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepositoryCustom {
    List<AllMemberResponse> allMember();

    Optional<Member> findByEmail(String email);
}
