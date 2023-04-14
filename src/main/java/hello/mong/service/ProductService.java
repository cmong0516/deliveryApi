package hello.mong.service;

import hello.mong.domain.entity.CanOrder;
import hello.mong.domain.entity.Product;
import hello.mong.domain.request.NewProductRequest;
import hello.mong.domain.response.NewProductResponse;
import hello.mong.repository.product.ProductJpaRepository;
import hello.mong.repository.product.ProductRepositoryCustom;
import hello.mong.repository.shop.ShopJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductJpaRepository productJpaRepository;
    private final ProductRepositoryCustom productRepositoryCustom;
    private final ShopJpaRepository shopJpaRepository;

    @Transactional
    public NewProductResponse newProduct(NewProductRequest request) {
        Product product = Product.builder()
                .name(request.getProductName())
                .price(request.getProductPrice())
                .shop(shopJpaRepository.findByName(request.getShopName())
                        .orElseThrow(() -> new RuntimeException(request.getShopName() + " 을 찾을수 없습니다.")))
                .state(CanOrder.CAN_ORDER)
                .build();

        productJpaRepository.save(product);

        return productRepositoryCustom.newProductResponse(product.getId());
    }
}
