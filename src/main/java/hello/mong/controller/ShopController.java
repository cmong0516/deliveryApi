package hello.mong.controller;

import hello.mong.domain.request.shop.NewShopRequest;
import hello.mong.domain.response.shop.NewShopResponse;
import hello.mong.domain.response.shop.ShopListById;
import hello.mong.service.ShopService;
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
public class ShopController {
    private final ShopService shopService;

    @PostMapping("/shop/new")
    public ResponseEntity<NewShopResponse> newShop(@Valid @RequestBody NewShopRequest request, HttpServletRequest httpServletRequest) {
        NewShopResponse newShopResponse = shopService.newShop(request,httpServletRequest);

        return new ResponseEntity<>(newShopResponse, HttpStatus.OK);
    }

    @PostMapping("/shop/my")
    public ResponseEntity<List<ShopListById>> myShop(HttpServletRequest httpServletRequest) {
        List<ShopListById> shopListByIds = shopService.shopListByMember(httpServletRequest);

        return new ResponseEntity<>(shopListByIds,HttpStatus.OK);
    }
}
