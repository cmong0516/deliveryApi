package hello.mong.service;

import hello.mong.domain.entity.Delivery;
import hello.mong.domain.request.NewDeliveryRequest;
import hello.mong.repository.delivery.DeliveryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryJpaRepository deliveryJpaRepository;

    public Long newDelivery(NewDeliveryRequest request) {
        Delivery delivery = Delivery.builder()
                .name(request.getName())
                .age(request.getAge())
                .city(request.getCity())
                .phone(request.getPhone())
                .build();

        deliveryJpaRepository.save(delivery);

        return delivery.getId();
    }
}
