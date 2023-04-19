package hello.mong.repository.order;

import static hello.mong.domain.entity.QDelivery.*;
import static hello.mong.domain.entity.QMember.*;
import static hello.mong.domain.entity.QOrders.*;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.mong.domain.entity.OrderState;
import hello.mong.domain.response.AllOrderResponse;
import hello.mong.domain.response.QAllOrderResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class OrderRepositoryImpl implements OrderRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<AllOrderResponse> allOrder() {

        return jpaQueryFactory.select(new QAllOrderResponse(orders.id,orders.member.name, orders.member.phone,
                        orders.product.shop.name, orders.product.shop.phone, orders.product.name, orders.product.price,
                        orders.quantity,
                        orders.delivery.name,
                        orders.delivery.phone,
                        orders.orderState))
                .from(orders)
                .leftJoin(orders.member,member)
                .leftJoin(orders.delivery,delivery)
                .fetch();
    }

    @Override
    public List<AllOrderResponse> notYetAllOrder() {

        return jpaQueryFactory.select(new QAllOrderResponse(orders.id,orders.member.name, orders.member.phone,
                        orders.product.shop.name, orders.product.shop.phone, orders.product.name, orders.product.price,
                        orders.quantity,
                        orders.delivery.name,
                        orders.delivery.phone,
                        orders.orderState))
                .from(orders)
                .where(orders.orderState.eq(OrderState.NOT_YET))
                .leftJoin(orders.member,member)
                .leftJoin(orders.delivery,delivery)
                .fetch();

    }
}
