package hello.mong.service;

import hello.mong.domain.entity.Member;
import hello.mong.domain.request.NewDeliveryRequest;
import hello.mong.domain.request.NewMemberRequest;
import hello.mong.domain.request.NewProductRequest;
import hello.mong.domain.request.NewShopRequest;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class InitService {

    private final MemberService memberService;
    private final DeliveryService deliveryService;
    private final ShopService shopService;
    private final ProductService productService;
    private final OrderService orderService;

    @PostConstruct
    public void init() {
        initMember();
        initDelivery();
        initShop();
        initProduct();

    }

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
    }

    public void initDelivery() {
        for (int i = 1; i < 11; i++) {
            NewDeliveryRequest request = NewDeliveryRequest.builder()
                    .deliveryName("delivery" + i)
                    .deliveryAge((int) (Math.random() * 100) + 1)
                    .deliveryPhone("010-1111-" + i)
                    .city("seoul")
                    .build();
            deliveryService.newDelivery(request);
        }
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
