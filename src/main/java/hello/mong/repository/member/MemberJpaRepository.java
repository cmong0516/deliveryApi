package hello.mong.repository.member;

import hello.mong.domain.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberJpaRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByEmail(String email);
}
