package hello.mong.service;

import hello.mong.repository.shop.ShopJpaRepository;
import hello.mong.repository.shop.ShopRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final ShopJpaRepository shopJpaRepository;
    private final ShopRepositoryCustom shopRepositoryCustom;
}
