package hello.mong.controller;

import hello.mong.domain.request.DeliveryPickUpRequest;
import hello.mong.domain.request.NewDeliveryRequest;
import hello.mong.domain.response.NewDeliveryResponse;
import hello.mong.domain.response.OrderResponse;
import hello.mong.service.DeliveryService;
import hello.mong.utils.JwtProvider;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;
    private final JwtProvider jwtProvider;

    @PostMapping("/delivery/new")
    public ResponseEntity<NewDeliveryResponse> newDelivery(@Valid @RequestBody NewDeliveryRequest request) {
        NewDeliveryResponse newDeliveryResponse = deliveryService.newDelivery(request);

        return new ResponseEntity<>(newDeliveryResponse, HttpStatus.OK);
    }

    @PostMapping("/delivery/pick-up")
    public ResponseEntity<DeliveryService> deliveryPickUp(@RequestBody DeliveryPickUpRequest request,
                                                          HttpServletRequest httpServletRequest) {
        OrderResponse orderResponse = deliveryService.pickUp(request,httpServletRequest);

        return new ResponseEntity<>(deliveryService, HttpStatus.OK);
    }

    @PostMapping("/delivery/test")
    public String deliveryTest(HttpServletRequest request) {

        String authorization = request.getHeader("Authorization");
        String substring = authorization.split(" ")[1].trim();
        String member = jwtProvider.getMember(substring);

        return member + " 은 delivery 입니다.";
    }
}
