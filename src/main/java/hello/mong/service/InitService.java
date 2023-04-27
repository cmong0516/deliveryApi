package hello.mong.service;

import hello.mong.domain.entity.Authority;
import hello.mong.domain.entity.Member;
import hello.mong.domain.entity.Shop;
import hello.mong.domain.request.member.NewMemberRequest;
import hello.mong.domain.request.product.NewProductRequest;
import hello.mong.domain.request.shop.NewShopRequest;
import hello.mong.repository.member.MemberJpaRepository;
import hello.mong.repository.shop.ShopJpaRepository;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class InitService {

    private final MemberService memberService;
    private final DeliveryService deliveryService;
    private final ShopService shopService;
    private final ProductService productService;
    private final OrderService orderService;
    private final MemberJpaRepository memberJpaRepository;
    private final ShopJpaRepository shopJpaRepository;


    @PostConstruct
    public void init() {
        initAdmin();
        initMember();
        initShop();
        initProduct();
    }


    @Transactional
    public void initMember() {
        for (int i = 1; i <= 50; i++) {
            NewMemberRequest request = NewMemberRequest.builder()
                    .email("user" + i+"@mong.com")
                    .password("123123")
                    .name("user" + i)
                    .phone("010-0000-" + i)
                    .build();
            memberService.signUp(request);
        }
    }

    public void initAdmin() {

        NewMemberRequest admin = NewMemberRequest.builder()
                .email("cmong0516@gmail.com")
                .password("123123")
                .name("김창모")
                .phone("010-5354-0000")
                .build();

        memberService.signUp(admin);

        Member adminMember = memberJpaRepository.findByEmail("cmong0516@gmail.com").get();

        Set<Authority> roles = new HashSet<>();

        roles.add(Authority.ROLE_ADMIN);
        roles.add(Authority.ROLE_DELIVERY);

        adminMember.setRoles(roles);

        memberJpaRepository.save(adminMember);
    }

    public void initShop() {

        Random random = new Random();

        for (int i = 1; i <= 30; i++) {

            Member member = memberJpaRepository.findByEmail(
                            "user" + (random.nextInt(50) + 1) + "@mong.com")
                    .orElseThrow(() -> new RuntimeException("InitShop Error"));

            NewShopRequest request = NewShopRequest.builder()
                    .shopName(("shop" + i))
                    .shopPhone(("02-000-" + i))
                    .city("seoul")
                    .build();

            Shop shop = Shop.builder()
                    .name(request.getShopName())
                    .phone(request.getShopPhone())
                    .city(request.getCity())
                    .build();

            shop.setMaster(member);

            shopJpaRepository.save(shop);

        }
    }

    public void initProduct() {
        for (int i = 1; i <= 200; i++) {
            NewProductRequest request = NewProductRequest.builder()
                    .productName("product" + i)
                    .productPrice((int) (Math.random() * 10000) + 1)
                    .shopName("shop" + shopNum((int) (Math.random() * 100) + 1))
                    .build();
            productService.newProduct(request);
        }
    }

    public int shopNum(int i) {
        if (i < 30) {
            return i;
        }

        i -= 29;
        return shopNum(i);
    }
}
