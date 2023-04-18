package hello.mong.service;

import hello.mong.domain.entity.Member;
import hello.mong.domain.entity.Order;
import hello.mong.domain.entity.OrderState;
import hello.mong.domain.entity.Product;
import hello.mong.domain.request.NewOrderRequest;
import hello.mong.domain.response.AllOrderResponse;
import hello.mong.domain.response.NewOrderResponse;
import hello.mong.repository.member.MemberJpaRepository;
import hello.mong.repository.order.OrderJpaRepository;
import hello.mong.repository.order.OrderRepositoryCustom;
import hello.mong.repository.product.ProductJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderRepositoryCustom orderRepositoryCustom;
    private final MemberJpaRepository memberJpaRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProductJpaRepository productJpaRepository;

    public NewOrderResponse newOrder(NewOrderRequest request) {
        Member member = memberJpaRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException(request.getEmail() + " 회원을 찾을수 없습니다."));

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 회원정보 입니다.");
        }

        Product product = productJpaRepository.findByName(request.getProductName())
                .orElseThrow(() -> new IllegalArgumentException("잘못된 상품 정보입니다."));

        Order order = Order.builder()
                .member(member)
                .product(product)
                .quantity(request.getProductQuantity())
                .orderState(OrderState.NOT_YET)
                .build();

        orderJpaRepository.save(order);

        return NewOrderResponse.builder()
                .memberId(member.getEmail())
                .memberPhone(member.getPhone())
                .shopName(product.getShop().getName())
                .shopPhone(product.getShop().getPhone())
                .productName(product.getName())
                .productPrice(product.getPrice())
                .quantity(request.getProductQuantity())
                .totalPrice(product.getPrice() * request.getProductQuantity())
                .orderState(order.getOrderState())
                .build();
    }

    public List<AllOrderResponse> allOrderResponses() {
        return orderRepositoryCustom.allOrder();
    }
}
