package hello.mong.controller;

import hello.mong.domain.request.delivery.DeliveryPickUpRequest;
import hello.mong.domain.request.delivery.NewDeliveryRequest;
import hello.mong.domain.response.order.AllOrderResponse;
import hello.mong.domain.response.delivery.NewDeliveryResponse;
import hello.mong.domain.response.order.OrderResponse;
import hello.mong.service.DeliveryService;
import hello.mong.utils.JwtProvider;
import java.util.List;
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
    public ResponseEntity<NewDeliveryResponse> newDelivery(@Valid @RequestBody NewDeliveryRequest request,HttpServletRequest servletRequest) {
        NewDeliveryResponse newDeliveryResponse = deliveryService.newDelivery(request,servletRequest);

        return new ResponseEntity<>(newDeliveryResponse, HttpStatus.OK);
    }

    @PostMapping("/delivery/pick-up")
    public ResponseEntity<OrderResponse> deliveryPickUp(@RequestBody DeliveryPickUpRequest request,
                                                        HttpServletRequest httpServletRequest) {
        OrderResponse orderResponse = deliveryService.pickUp(request, httpServletRequest);

        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    @PostMapping("/delivery/can-pick-up")
    public ResponseEntity<List<AllOrderResponse>> allOrderForDelivery() {
        List<AllOrderResponse> canPickUpOrders = deliveryService.canPickUpOrders();

        return new ResponseEntity<>(canPickUpOrders,HttpStatus.OK);
    }
}
