package hello.mong.repository.shop;

import hello.mong.domain.entity.Shop;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopJpaRepository extends JpaRepository<Shop,Long> {
    Optional<Shop> findByName(String name);
}
