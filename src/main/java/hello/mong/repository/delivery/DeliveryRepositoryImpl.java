package hello.mong.repository.delivery;

import hello.mong.domain.entity.Delivery;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class DeliveryRepositoryImpl implements DeliveryRepositoryCustom{
    @Override
    public Optional<Delivery> findByName(String deliveryName) {
        return Optional.empty();
    }
}
