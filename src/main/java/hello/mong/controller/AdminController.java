package hello.mong.controller;

import hello.mong.domain.response.AllDeliveryResponse;
import hello.mong.domain.response.AllMemberResponse;
import hello.mong.domain.response.AllOrderResponse;
import hello.mong.service.DeliveryService;
import hello.mong.service.MemberService;
import hello.mong.service.OrderService;
import java.util.List;
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
    public void allProduct() {

    }
}
