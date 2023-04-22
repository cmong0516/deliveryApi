package hello.mong.repository.delivery;

import hello.mong.domain.entity.Delivery;
import hello.mong.domain.response.delivery.AllDeliveryResponse;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepositoryCustom {
    Optional<Delivery> findByName(String deliveryName);

    List<AllDeliveryResponse> allDelivery();
}
