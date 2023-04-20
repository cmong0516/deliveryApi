package hello.mong.repository.member;

import static hello.mong.domain.entity.QMember.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.mong.domain.entity.QMember;
import hello.mong.domain.response.AllMemberResponse;
import hello.mong.domain.response.QAllMemberResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<AllMemberResponse> allMember() {
        return jpaQueryFactory.select(new QAllMemberResponse(member.id, member.email,member.name,member.phone))
                .from(member)
                .fetch();
    }
}
