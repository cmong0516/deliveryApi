package hello.mong.domain.response;

import com.querydsl.core.annotations.QueryProjection;
import hello.mong.domain.entity.OrderState;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AllOrderResponse {
    private String clientName;
    private String clientPhone;
    private String shopName;
    private String shopPhone;
    private String productName;
    private int productPrice;
    private int orderQuantity;
    private int orderTotalPrice;
    private String deliveryName;
    private String deliveryPhone;
    private OrderState orderState;


    @QueryProjection
    public AllOrderResponse(String clientName, String clientPhone, String shopName, String shopPhone,
                            String productName, int productPrice, int orderQuantity, int orderTotalPrice,
                            String deliveryName, String deliveryPhone, OrderState orderState) {
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        this.shopName = shopName;
        this.shopPhone = shopPhone;
        this.productName = productName;
        this.productPrice = productPrice;
        this.orderQuantity = orderQuantity;
        this.orderTotalPrice = orderTotalPrice;
        this.deliveryName = deliveryName;
        this.deliveryPhone = deliveryPhone;
        this.orderState = orderState;
    }
}
