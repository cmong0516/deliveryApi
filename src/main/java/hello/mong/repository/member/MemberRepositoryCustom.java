package hello.mong.repository.member;

import hello.mong.domain.response.member.AllMemberResponse;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepositoryCustom {
    List<AllMemberResponse> allMember();
}
