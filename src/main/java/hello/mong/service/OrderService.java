package hello.mong.service;

import hello.mong.domain.entity.Member;
import hello.mong.domain.entity.OrderState;
import hello.mong.domain.entity.Orders;
import hello.mong.domain.entity.Product;
import hello.mong.domain.request.NewOrderRequest;
import hello.mong.domain.response.AllOrderResponse;
import hello.mong.domain.response.NewOrderResponse;
import hello.mong.repository.member.MemberJpaRepository;
import hello.mong.repository.order.OrderJpaRepository;
import hello.mong.repository.order.OrderRepositoryCustom;
import hello.mong.repository.product.ProductJpaRepository;
import hello.mong.utils.JwtProvider;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
    private final JwtProvider jwtProvider;

    public NewOrderResponse newOrder(NewOrderRequest request, HttpServletRequest httpServletRequest) {



        String authorization = httpServletRequest.getHeader("Authorization");

        String token = authorization.split(" ")[1].trim();

        String memberEmail = jwtProvider.getMember(token);

        if (!request.getEmail().equals(memberEmail)) {
            throw new RuntimeException("로그인 유저 정보와 토큰 정보가 일치하지 않습니다.");
        }

        Member member = memberJpaRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException(request.getEmail() + " 회원을 찾을수 없습니다."));

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 회원정보 입니다.");
        }

        Product product = productJpaRepository.findByName(request.getProductName())
                .orElseThrow(() -> new IllegalArgumentException("잘못된 상품 정보입니다."));

        Orders orders = Orders.builder()
                .member(member)
                .product(product)
                .quantity(request.getProductQuantity())
                .orderState(OrderState.NOT_YET)
                .build();

        orderJpaRepository.save(orders);

        return NewOrderResponse.builder()
                .memberId(member.getEmail())
                .memberPhone(member.getPhone())
                .shopName(product.getShop().getName())
                .shopPhone(product.getShop().getPhone())
                .productName(product.getName())
                .productPrice(product.getPrice())
                .quantity(request.getProductQuantity())
                .totalPrice(product.getPrice() * request.getProductQuantity())
                .orderState(orders.getOrderState())
                .build();
    }

    public List<AllOrderResponse> allOrderResponses() {
        return orderRepositoryCustom.allOrder();
    }
}
