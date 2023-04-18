package hello.mong.controller;

import hello.mong.domain.request.NewOrderRequest;
import hello.mong.domain.response.AllOrderResponse;
import hello.mong.domain.response.NewOrderResponse;
import hello.mong.service.OrderService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order/new")
    public ResponseEntity<NewOrderResponse> newOrder(@Valid @RequestBody NewOrderRequest request) {

        NewOrderResponse newOrderResponse = orderService.newOrder(request);

        return new ResponseEntity<>(newOrderResponse, HttpStatus.OK);
    }

    @GetMapping("/order/all")
    public ResponseEntity<List<AllOrderResponse>> allOrder() {

        List<AllOrderResponse> allOrderResponses = orderService.allOrderResponses();

        return new ResponseEntity<>(allOrderResponses, HttpStatus.OK);
    }
}
