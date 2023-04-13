package hello.mong.repository.product;

import static hello.mong.domain.entity.QProduct.product;
import static hello.mong.domain.entity.QShop.shop;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.mong.domain.entity.QProduct;
import hello.mong.domain.entity.QShop;
import hello.mong.domain.response.NewProductResponse;
import hello.mong.domain.response.QNewProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public NewProductResponse newProductResponse(Long id) {
        return jpaQueryFactory.select(
                        new QNewProductResponse(product.name, product.price, product.state, product.shop.name,
                                product.shop.phone,
                                product.shop.city))
                .from(product)
                .where(product.id.eq(id))
                .fetchOne();
    }
}
