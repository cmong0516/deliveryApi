package hello.mong.repository.order;

import hello.mong.domain.entity.Orders;
import hello.mong.domain.response.AllOrderResponse;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepositoryCustom {
    List<AllOrderResponse> allOrder();
    List<AllOrderResponse> notYetAllOrder();

    List<Long> findByDeliveryId(Long deliveryId);
}
