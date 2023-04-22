package hello.mong.controller;

import hello.mong.domain.request.order.NewOrderRequest;
import hello.mong.domain.response.order.NewOrderResponse;
import hello.mong.service.OrderService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order/new")
    public ResponseEntity<NewOrderResponse> newOrder(@Valid @RequestBody NewOrderRequest request, HttpServletRequest httpServletRequest) {

        NewOrderResponse newOrderResponse = orderService.newOrder(request,httpServletRequest);

        return new ResponseEntity<>(newOrderResponse, HttpStatus.OK);
    }


}
