package hello.mong.repository.member;

import static hello.mong.domain.entity.QAuthority.*;
import static hello.mong.domain.entity.QMember.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.mong.domain.entity.Member;
import hello.mong.domain.response.member.AllMemberResponse;
import hello.mong.domain.response.member.QAllMemberResponse;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<AllMemberResponse> allMember() {
        return jpaQueryFactory.select(new QAllMemberResponse(member.id, member.email, member.name, member.phone))
                .from(member)
                .fetch();
    }

    @Override
    public Optional<Member> findByEmail(String email) {

        return Optional.ofNullable(jpaQueryFactory.selectFrom(member)
                .leftJoin(member.roles, authority)
                .fetchJoin()
                .where(member.email.eq(email))
                .fetchOne());

    }
}
