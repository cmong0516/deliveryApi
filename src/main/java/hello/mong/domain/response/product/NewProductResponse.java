package hello.mong.domain.response.product;

import com.querydsl.core.annotations.QueryProjection;
import hello.mong.domain.entity.CanOrder;
import lombok.Data;

@Data
public class NewProductResponse {
    private String productName;
    private int productPrice;
    private CanOrder productState;
    private String shopName;
    private String shopPhone;
    private String shopCity;

    @QueryProjection
    public NewProductResponse(String name, int price, CanOrder state, String shopName, String shopPhone,
                              String shopCity) {
        this.productName = name;
        this.productPrice = price;
        this.productState = state;
        this.shopName = shopName;
        this.shopPhone = shopPhone;
        this.shopCity = shopCity;
    }
}
