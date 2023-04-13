package hello.mong.service;

import hello.mong.repository.product.ProductJpaRepository;
import hello.mong.repository.product.ProductRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductJpaRepository productJpaRepository;
    private final ProductRepositoryCustom productRepositoryCustom;
}
