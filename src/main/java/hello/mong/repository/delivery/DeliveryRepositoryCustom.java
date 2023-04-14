package hello.mong.repository.delivery;

import hello.mong.domain.entity.Delivery;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepositoryCustom {
    Optional<Delivery> findByName(String deliveryName);
}
