package hello.mong.domain.response;

import hello.mong.domain.entity.OrderState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewOrderResponse {

    private String memberId;
    private String memberPhone;
    private String shopName;
    private String shopPhone;
    private String productName;
    private int productPrice;
    private int quantity;
    private int totalPrice;
    private OrderState orderState;

}
