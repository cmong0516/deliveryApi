package hello.mong.repository.order;

import static hello.mong.domain.entity.QOrder.order;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.mong.domain.entity.QOrder;
import hello.mong.domain.response.AllOrderResponse;
import hello.mong.domain.response.QAllOrderResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<AllOrderResponse> allOrder() {

        jpaQueryFactory.select(new QAllOrderResponse(order.member.name,order.member.phone,
                order.product.shop.name,order.product.shop.phone,order.product.name,order.product.price,))
        return null;
    }
}
