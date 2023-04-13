package hello.mong.domain.response;

import com.querydsl.core.annotations.QueryProjection;
import hello.mong.domain.entity.CanOrder;
import lombok.Data;

@Data
public class NewProductResponse {
    private String name;
    private int price;
    private CanOrder state;
    private String shopName;
    private String shopPhone;
    private String shopCity;

    @QueryProjection
    public NewProductResponse(String name, int price, CanOrder state, String shopName, String shopPhone,
                              String shopCity) {
        this.name = name;
        this.price = price;
        this.state = state;
        this.shopName = shopName;
        this.shopPhone = shopPhone;
        this.shopCity = shopCity;
    }
}
