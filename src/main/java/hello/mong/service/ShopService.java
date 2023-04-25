package hello.mong.service;

import hello.mong.domain.entity.Member;
import hello.mong.domain.entity.Product;
import hello.mong.domain.entity.Shop;
import hello.mong.domain.request.shop.NewShopRequest;
import hello.mong.domain.response.member.AllMemberResponse;
import hello.mong.domain.response.product.ProductResponse;
import hello.mong.domain.response.shop.AllShopResponse;
import hello.mong.domain.response.shop.NewShopResponse;
import hello.mong.domain.response.shop.ShopListById;
import hello.mong.repository.member.MemberJpaRepository;
import hello.mong.repository.product.ProductRepositoryCustom;
import hello.mong.repository.shop.ShopJpaRepository;
import hello.mong.repository.shop.ShopRepositoryCustom;
import hello.mong.utils.JwtProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShopService {
    private final ShopJpaRepository shopJpaRepository;
    private final ShopRepositoryCustom shopRepositoryCustom;
    private final JwtProvider jwtProvider;
    private final MemberJpaRepository memberJpaRepository;
    private final ProductRepositoryCustom productRepositoryCustom;

    public NewShopResponse newShop(NewShopRequest request, HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");

        String token = authorization.split(" ")[1].trim();

        String memberEmail = jwtProvider.getMember(token);

        Member member = memberJpaRepository.findByEmail(memberEmail)
                .orElseThrow(() -> new IllegalStateException("잘못된 회원정보 입니다."));

        Shop shop = Shop.builder()
                .name(request.getShopName())
                .phone(request.getShopPhone())
                .city(request.getCity().toUpperCase())
                .build();

        shop.setMaster(member);

        shopJpaRepository.save(shop);

        return NewShopResponse.builder()
                .shopName(shop.getName())
                .shopPhone(shop.getPhone())
                .city(shop.getCity())
                .masterName(shop.getMaster().getName())
                .masterPhone(shop.getMaster().getPhone())
                .masterEmail(shop.getMaster().getEmail())
                .build();
    }

    public List<ShopListById> shopListByMember(HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");

        String token = authorization.split(" ")[1].trim();

        String memberEmail = jwtProvider.getMember(token);

        Member member = memberJpaRepository.findByEmail(memberEmail)
                .orElseThrow(() -> new IllegalStateException("잘못된 회원정보 입니다."));

        return shopRepositoryCustom.shopListByMember(member);
    }

    public List<AllShopResponse> allShop() {
        List<AllShopResponse> result = new ArrayList<>();

        List<Shop> shops = shopRepositoryCustom.allShop();

        log.info("shops.size() = {}" , shops.size());

        for (Shop shop : shops) {

            List<ProductResponse> products = shop.getProducts().stream().map(product -> ProductResponse.builder()
                            .productName(product.getName())
                            .productPrice(product.getPrice())
                            .canOrderState(product.getState())
                            .build())
                    .collect(Collectors.toList());

            AllShopResponse allShopResponse = AllShopResponse.builder()
                    .shopId(shop.getId())
                    .shopName(shop.getName())
                    .shopPhone(shop.getPhone())
                    .city(shop.getCity())
                    .master(
                            AllMemberResponse.builder()
                                    .id(shop.getMaster().getId())
                                    .email(shop.getMaster().getEmail())
                                    .username(shop.getMaster().getName())
                                    .phone(shop.getMaster().getPhone()).build())
                    .product(products)
                    .build();

            result.add(allShopResponse);
        }

        return result;
    }
}
