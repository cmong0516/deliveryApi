package hello.mong.service;

import hello.mong.domain.entity.Delivery;
import hello.mong.domain.request.NewDeliveryRequest;
import hello.mong.domain.response.NewDeliveryResponse;
import hello.mong.repository.delivery.DeliveryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryJpaRepository deliveryJpaRepository;

    public NewDeliveryResponse newDelivery(NewDeliveryRequest request) {
        Delivery delivery = Delivery.builder()
                .name(request.getDeliveryName())
                .age(request.getDeliveryAge())
                .city(request.getCity().toUpperCase())
                .phone(request.getDeliveryPhone())
                .build();

        deliveryJpaRepository.save(delivery);

        return NewDeliveryResponse.builder()
                .deliveryName(delivery.getName())
                .deliveryAge(delivery.getAge())
                .city(delivery.getCity())
                .phone(delivery.getPhone())
                .build();
    }
}
