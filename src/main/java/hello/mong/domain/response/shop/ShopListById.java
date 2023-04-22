package hello.mong.domain.response.shop;

import com.querydsl.core.annotations.QueryProjection;
import hello.mong.domain.response.product.ProductResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShopListById {
    private String shopName;
    private String shopPhone;
    private String city;
    private List<ProductResponse> products = new ArrayList<>();

    @QueryProjection
    public ShopListById(String shopName, String shopPhone, String city) {
        this.shopName = shopName;
        this.shopPhone = shopPhone;
        this.city = city;
    }

    public void setProducts(List<ProductResponse> products) {
        this.products = products;
    }
}
