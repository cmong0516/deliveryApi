package hello.mong.domain.request;

import lombok.Data;

@Data
public class NewProductRequest {
    private String productName;
    private int productPrice;
    private String shopName;
}
