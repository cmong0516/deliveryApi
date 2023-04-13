package hello.mong.repository.product;

import hello.mong.domain.response.NewProductResponse;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositoryCustom {
    public NewProductResponse newProductResponse(Long id);
}
