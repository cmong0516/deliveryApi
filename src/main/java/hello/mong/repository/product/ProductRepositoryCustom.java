package hello.mong.repository.product;

import hello.mong.domain.entity.Shop;
import hello.mong.domain.response.product.NewProductResponse;
import hello.mong.domain.response.product.ProductResponse;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositoryCustom {
    NewProductResponse newProductResponse(Long id);

    List<NewProductResponse> allProduct();

    List<ProductResponse> productByShop(String shopName);
}
