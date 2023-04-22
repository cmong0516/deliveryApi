package hello.mong.service;

import hello.mong.domain.entity.Member;
import hello.mong.domain.entity.Shop;
import hello.mong.domain.request.shop.NewShopRequest;
import hello.mong.domain.response.shop.NewShopResponse;
import hello.mong.domain.response.shop.ShopListById;
import hello.mong.repository.member.MemberJpaRepository;
import hello.mong.repository.product.ProductRepositoryCustom;
import hello.mong.repository.shop.ShopJpaRepository;
import hello.mong.repository.shop.ShopRepositoryCustom;
import hello.mong.utils.JwtProvider;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
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
}
