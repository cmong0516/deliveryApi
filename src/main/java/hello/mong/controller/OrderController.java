package hello.mong.controller;

import hello.mong.domain.request.NewOrderRequest;
import hello.mong.domain.response.NewOrderResponse;
import hello.mong.service.OrderService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order/new")
    public ResponseEntity<NewOrderResponse> newOrder(@Valid @RequestBody NewOrderRequest request) {

        NewOrderResponse newOrderResponse = orderService.newOrder(request);

        return new ResponseEntity<>(newOrderResponse, HttpStatus.OK);
    }
}
