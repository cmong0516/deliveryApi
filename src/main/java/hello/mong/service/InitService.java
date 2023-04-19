package hello.mong.service;

import hello.mong.domain.entity.Authority;
import hello.mong.domain.entity.Member;
import hello.mong.domain.request.NewDeliveryRequest;
import hello.mong.domain.request.NewMemberRequest;
import hello.mong.domain.request.NewProductRequest;
import hello.mong.domain.request.NewShopRequest;
import hello.mong.repository.member.MemberJpaRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class InitService {

    private final MemberService memberService;
    private final DeliveryService deliveryService;
    private final ShopService shopService;
    private final ProductService productService;
    private final OrderService orderService;
    private final MemberJpaRepository memberJpaRepository;

    @PostConstruct
    public void init() {
        initMember();
        initShop();
        initProduct();

    }

    @Transactional
    public void initMember() {
        for (int i = 1; i < 50; i++) {
            NewMemberRequest request = NewMemberRequest.builder()
                    .email("user" + i+"@mong.com")
                    .password("123123")
                    .name("user" + i)
                    .phone("010-0000-" + i)
                    .build();
            memberService.signUp(request);
        }

        NewMemberRequest admin = NewMemberRequest.builder()
                .email("cmong0516@gmail.com")
                .password("123123")
                .name("김창모")
                .phone("010-5354-0000")
                .build();

        memberService.signUp(admin);

        Member adminMember = memberJpaRepository.findByEmail("cmong0516@gmail.com").get();

        List<Authority> roles = adminMember.getRoles();

        roles.add(Authority.builder().name("ROLE_ADMIN").build());
        roles.add(Authority.builder().name("ROLE_DELIVERY").build());

        adminMember.setRoles(roles);

        memberJpaRepository.save(adminMember);
    }



    public void initShop() {
        for (int i = 0; i < 30; i++) {
            NewShopRequest request = NewShopRequest.builder()
                    .shopName("shop" + i)
                    .shopPhone("02-0000-" + i)
                    .city("seoul")
                    .build();
            shopService.newShop(request);
        }
    }

    public void initProduct() {
        for (int i = 0; i < 200; i++) {
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

        i -= 30;
        return shopNum(i);
    }
}
