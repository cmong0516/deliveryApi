package hello.mong.service;

import hello.mong.domain.entity.Authority;
import hello.mong.domain.entity.Delivery;
import hello.mong.domain.entity.Member;
import hello.mong.domain.entity.OrderState;
import hello.mong.domain.entity.Orders;
import hello.mong.domain.request.DeliveryPickUpRequest;
import hello.mong.domain.request.NewDeliveryRequest;
import hello.mong.domain.response.NewDeliveryResponse;
import hello.mong.domain.response.OrderResponse;
import hello.mong.repository.delivery.DeliveryJpaRepository;
import hello.mong.repository.member.MemberJpaRepository;
import hello.mong.repository.order.OrderJpaRepository;
import hello.mong.utils.JwtProvider;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryService {

    private final DeliveryJpaRepository deliveryJpaRepository;
    private final OrderJpaRepository orderJpaRepository;
    private final MemberJpaRepository memberJpaRepository;
    private final JwtProvider jwtProvider;

    public NewDeliveryResponse newDelivery(NewDeliveryRequest request) {

        String email = request.getEmail();

        Member member = memberJpaRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(email + " 유저를 찾을수 없습니다."));

        List<Authority> roles = member.getRoles();

        roles.add(roles.size(), Authority.builder().name("ROLE_DELIVERY").build());

        member.setRoles(roles);

        Delivery delivery = Delivery.builder()
                .name(member.getName())
                .phone(member.getPhone())
                .build();

        deliveryJpaRepository.save(delivery);

        String token = jwtProvider.createToken(member.getEmail(), member.getRoles());

        return NewDeliveryResponse.builder()
                .deliveryName(delivery.getName())
                .phone(delivery.getPhone())
                .token(token)
                .build();
    }

    @Transactional
    public OrderResponse pickUp(DeliveryPickUpRequest request, HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");

        String token = authorization.split(" ")[1].trim();

        String memberEmail = jwtProvider.getMember(token);

        Member member = memberJpaRepository.findByEmail(memberEmail)
                .orElseThrow(() -> new IllegalArgumentException(memberEmail + " 유저를 찾을수 없습니다."));

        Delivery delivery = deliveryJpaRepository.findById(member.getId())
                .orElseThrow(() -> new IllegalArgumentException(member.getId() + " 를 찾을수 없습니다."));

        Orders orders = orderJpaRepository.findById(request.getOrderId())
                .orElseThrow(() -> new IllegalArgumentException(request.getOrderId() + " 를 찾을수 없습니다."));

        orders.setDelivery(delivery);

        return OrderResponse.builder()
                .memberId(orders.getMember().getEmail())
                .memberPhone(orders.getMember().getPhone())
                .shopName(orders.getProduct().getShop().getName())
                .shopPhone(orders.getProduct().getShop().getPhone())
                .productName(orders.getProduct().getName())
                .productPrice(orders.getProduct().getPrice())
                .quantity(orders.getQuantity())
                .totalPrice(orders.getQuantity() * orders.getProduct().getPrice())
                .orderState(OrderState.PROGRESS)
                .deliveryName(orders.getDelivery().getName())
                .deliveryPhone(orders.getDelivery().getPhone())
                .build();

    }
}
