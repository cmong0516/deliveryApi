package hello.mong.service;

import hello.mong.domain.entity.Delivery;
import hello.mong.domain.entity.Order;
import hello.mong.domain.entity.OrderState;
import hello.mong.domain.request.DeliveryPickUpRequest;
import hello.mong.domain.request.NewDeliveryRequest;
import hello.mong.domain.response.NewDeliveryResponse;
import hello.mong.domain.response.OrderResponse;
import hello.mong.repository.delivery.DeliveryJpaRepository;
import hello.mong.repository.order.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryJpaRepository deliveryJpaRepository;
    private final OrderJpaRepository orderJpaRepository;

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

    @Transactional
    public OrderResponse pickUp(Long id, DeliveryPickUpRequest request) {
        Delivery delivery = deliveryJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + " 를 찾을수 없습니다."));

        Order order = orderJpaRepository.findById(request.getOrderId())
                .orElseThrow(() -> new IllegalArgumentException(request.getOrderId() + " 를 찾을수 없습니다."));

        order.setDelivery(delivery);

        return OrderResponse.builder()
                .memberId(order.getMember().getEmail())
                .memberPhone(order.getMember().getPhone())
                .shopName(order.getProduct().getShop().getName())
                .shopPhone(order.getProduct().getShop().getPhone())
                .productName(order.getProduct().getName())
                .productPrice(order.getProduct().getPrice())
                .quantity(order.getQuantity())
                .totalPrice(order.getQuantity() * order.getProduct().getPrice())
                .orderState(OrderState.PROGRESS)
                .deliveryName(order.getDelivery().getName())
                .deliveryPhone(order.getDelivery().getPhone())
                .build();

    }
}
