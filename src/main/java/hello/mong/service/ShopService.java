package hello.mong.service;

import hello.mong.domain.entity.Shop;
import hello.mong.domain.request.NewShopRequest;
import hello.mong.domain.response.NewShopResponse;
import hello.mong.repository.shop.ShopJpaRepository;
import hello.mong.repository.shop.ShopRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final ShopJpaRepository shopJpaRepository;
    private final ShopRepositoryCustom shopRepositoryCustom;

    public NewShopResponse newShop(NewShopRequest request) {
        Shop shop = Shop.builder()
                .name(request.getShopName())
                .phone(request.getShopPhone())
                .city(request.getCity().toUpperCase())
                .build();

        shopJpaRepository.save(shop);

        return NewShopResponse.builder()
                .shopName(shop.getName())
                .shopPhone(shop.getPhone())
                .city(shop.getCity())
                .build();
    }
}
