package hello.mong.repository.authority;

import hello.mong.domain.entity.Authority;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityJpaRepository extends JpaRepository<Authority,Long> {
    Optional<Authority> findByName(String name);
}
