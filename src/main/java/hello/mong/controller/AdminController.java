package hello.mong.controller;

import hello.mong.domain.response.delivery.AllDeliveryResponse;
import hello.mong.domain.response.member.AllMemberResponse;
import hello.mong.domain.response.order.AllOrderResponse;
import hello.mong.domain.response.product.NewProductResponse;
import hello.mong.domain.response.shop.AllShopResponse;
import hello.mong.service.DeliveryService;
import hello.mong.service.MemberService;
import hello.mong.service.OrderService;
import hello.mong.service.ProductService;
import hello.mong.service.ShopService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final DeliveryService deliveryService;
    private final ProductService productService;
    private final ShopService shopService;

    @GetMapping("/admin/all/order")
    public ResponseEntity<List<AllOrderResponse>> allOrder() {

        List<AllOrderResponse> allOrderResponses = orderService.allOrderResponses();

        return new ResponseEntity<>(allOrderResponses, HttpStatus.OK);
    }

    @GetMapping("/admin/all/user")
    public ResponseEntity<List<AllMemberResponse>> allUser() {
        List<AllMemberResponse> allMemberResponses = memberService.allMember();

        return new ResponseEntity<>(allMemberResponses,HttpStatus.OK);
    }

    @GetMapping("/admin/all/delivery")
    public ResponseEntity<List<AllDeliveryResponse>> allDelivery() {
        List<AllDeliveryResponse> allDeliveryResponses = deliveryService.allDelivery();

        return new ResponseEntity<>(allDeliveryResponses,HttpStatus.OK);
    }

    @GetMapping("/admin/all/product")
    public ResponseEntity<List<NewProductResponse>> allProduct() {
        List<NewProductResponse> newProductResponses = productService.allProduct();

        return new ResponseEntity<>(newProductResponses, HttpStatus.OK);
    }

    @GetMapping("/admin/all/shop")
    public ResponseEntity<List<AllShopResponse>> allShop() {
        List<AllShopResponse> allShopResponses = shopService.allShop();

        return new ResponseEntity<>(allShopResponses,HttpStatus.OK);
    }

    @GetMapping("/admin/test")
    public String adminTest() {

        return "admin 입니다.";
    }
}
