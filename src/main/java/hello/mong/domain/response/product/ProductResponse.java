package hello.mong.domain.response.product;

import com.querydsl.core.annotations.QueryProjection;
import hello.mong.domain.entity.CanOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class ProductResponse {
    private String productName;
    private int productPrice;
    private CanOrder canOrderState;

    @QueryProjection
    public ProductResponse(String productName, int productPrice, CanOrder canOrderState) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.canOrderState = canOrderState;
    }
}
