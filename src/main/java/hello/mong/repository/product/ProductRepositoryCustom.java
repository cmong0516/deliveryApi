package hello.mong.repository.product;

import hello.mong.domain.response.NewProductResponse;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositoryCustom {
    NewProductResponse newProductResponse(Long id);

    List<NewProductResponse> allProduct();
}
