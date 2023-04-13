package hello.mong.controller;

import hello.mong.domain.request.NewDeliveryRequest;
import hello.mong.domain.response.NewDeliveryResponse;
import hello.mong.service.DeliveryService;
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

    @PostMapping("/delivery/new")
    public ResponseEntity<NewDeliveryResponse> newDelivery(@Valid @RequestBody NewDeliveryRequest request) {
        NewDeliveryResponse newDeliveryResponse = deliveryService.newDelivery(request);

        return new ResponseEntity<>(newDeliveryResponse, HttpStatus.OK);
    }
}
