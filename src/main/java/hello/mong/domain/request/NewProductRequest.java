package hello.mong.domain.request;

import lombok.Data;

@Data
public class NewProductRequest {
    private String name;
    private int price;
    private String shopName;
}
