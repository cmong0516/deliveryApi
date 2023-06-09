package hello.mong.domain.response.order;

import com.querydsl.core.annotations.QueryProjection;
import hello.mong.domain.entity.OrderState;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AllOrderResponse {

    private Long orderId;
    private String clientName;
    private String clientEmail;
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
    public AllOrderResponse(Long orderId,String clientName,String clientEmail, String clientPhone, String shopName, String shopPhone,
                            String productName, int productPrice, int orderQuantity,String deliveryName, String deliveryPhone, OrderState orderState) {
        this.orderId = orderId;
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.clientPhone = clientPhone;
        this.shopName = shopName;
        this.shopPhone = shopPhone;
        this.productName = productName;
        this.productPrice = productPrice;
        this.orderQuantity = orderQuantity;
        this.orderTotalPrice = productPrice * orderQuantity;
        this.deliveryName = deliveryName;
        this.deliveryPhone = deliveryPhone;
        this.orderState = orderState;
    }
}
