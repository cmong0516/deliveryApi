package hello.mong.repository.shop;

import static hello.mong.domain.entity.QShop.shop;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.mong.domain.entity.Member;
import hello.mong.domain.entity.QShop;
import hello.mong.domain.response.shop.QShopListById;
import hello.mong.domain.response.shop.ShopListById;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ShopRepositoryImpl implements ShopRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ShopListById> shopListByMember(Member member) {

        return jpaQueryFactory.select(new QShopListById(shop.name, shop.phone, shop.city))
                .from(shop)
                .where(shop.master.eq(member))
                .fetch();
    }
}
