package hello.mong.repository.shop;

import static hello.mong.domain.entity.QAuthority.authority;
import static hello.mong.domain.entity.QMember.member;
import static hello.mong.domain.entity.QProduct.product;
import static hello.mong.domain.entity.QShop.shop;


import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.mong.domain.entity.Member;
import hello.mong.domain.entity.QAuthority;
import hello.mong.domain.entity.QMember;
import hello.mong.domain.entity.QProduct;
import hello.mong.domain.entity.Shop;
import hello.mong.domain.response.product.QProductResponse;
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

        List<ShopListById> shopListById = jpaQueryFactory.select(new QShopListById(shop.name, shop.phone, shop.city))
                .from(shop)
                .where(shop.master.eq(member))
                .fetch();

        for (ShopListById listById : shopListById) {
            listById.setProducts(
                    jpaQueryFactory.select(new QProductResponse(product.name, product.price, product.state))
                            .from(product)
                            .join(shop.master, QMember.member)
                            .where(product.shop.name.eq(listById.getShopName()))
                            .fetch()
            );
        }

        return shopListById;
    }

    @Override
    public List<Shop> allShop() {

        return jpaQueryFactory
                .selectDistinct(shop)
                .from(shop)
                .leftJoin(shop.master, member)
                .fetchJoin()
                .leftJoin(shop.products, product)
                .fetchJoin()
                .orderBy(shop.id.asc())
                .fetch();
    }


}
