package hello.mong.controller;

import hello.mong.domain.request.NewOrderRequest;
import hello.mong.service.OrderService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order/new")
    public void newOrder(@Valid @RequestBody NewOrderRequest request) {
        orderService.newOrder(request);
    }
}
