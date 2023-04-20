package hello.mong.repository.delivery;

import static hello.mong.domain.entity.QDelivery.delivery;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.mong.domain.entity.Delivery;
import hello.mong.domain.response.AllDeliveryResponse;
import hello.mong.domain.response.QAllDeliveryResponse;
import hello.mong.repository.order.OrderRepositoryCustom;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DeliveryRepositoryImpl implements DeliveryRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final OrderRepositoryCustom orderRepositoryCustom;

    @Override
    public Optional<Delivery> findByName(String deliveryName) {
        return Optional.empty();
    }

    @Override
    public List<AllDeliveryResponse> allDelivery() {

        List<AllDeliveryResponse> fetch = jpaQueryFactory.select(
                        new QAllDeliveryResponse(delivery.id, delivery.name, delivery.phone))
                .from(delivery)
                .fetch();

        for (AllDeliveryResponse allDeliveryResponse : fetch) {
            allDeliveryResponse.setOrderIds(orderRepositoryCustom.findByDeliveryId(allDeliveryResponse.getId()));
        }

        return fetch;
    }
}
