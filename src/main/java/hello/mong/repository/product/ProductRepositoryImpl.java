package hello.mong.repository.product;

import static hello.mong.domain.entity.QProduct.product;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.mong.domain.entity.QShop;
import hello.mong.domain.response.product.NewProductResponse;
import hello.mong.domain.response.product.ProductResponse;
import hello.mong.domain.response.product.QNewProductResponse;
import hello.mong.domain.response.product.QProductResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {

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

    @Override
    public List<NewProductResponse> allProduct() {
        return jpaQueryFactory.select(
                        new QNewProductResponse(product.name, product.price, product.state, product.shop.name,
                                product.shop.phone,
                                product.shop.city))
                .from(product)
                .join(product.shop,QShop.shop)
                .fetch();
    }

    @Override
    public List<ProductResponse> productByShop(String shopName) {

        return jpaQueryFactory.select(new QProductResponse(product.name, product.price, product.state))
                .from(product)
                .where(product.shop.name.eq(shopName))
                .fetch();
    }
}
