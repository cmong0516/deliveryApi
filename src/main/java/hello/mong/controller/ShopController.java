package hello.mong.controller;

import hello.mong.domain.request.NewShopRequest;
import hello.mong.domain.response.NewShopResponse;
import hello.mong.service.ShopService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShopController {
    private final ShopService shopService;

    @PostMapping("/shop/new")
    public ResponseEntity<NewShopResponse> newShop(@Valid @RequestBody NewShopRequest request) {
        NewShopResponse newShopResponse = shopService.newShop(request);

        return new ResponseEntity<>(newShopResponse, HttpStatus.OK);
    }
}
