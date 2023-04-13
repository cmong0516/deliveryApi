package hello.mong.controller;

import hello.mong.domain.request.NewProductRequest;
import hello.mong.domain.response.NewProductResponse;
import hello.mong.service.ProductService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/product/new")
    public ResponseEntity<NewProductResponse> newProduct(@Valid @RequestBody NewProductRequest request) {
        NewProductResponse newProductResponse = productService.newProduct(request);

        return new ResponseEntity<>(newProductResponse, HttpStatus.OK);
    }
}
