package hello.mong.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewDeliveryResponse {

    private String deliveryName;
    private int deliveryAge;
    private String city;
    private String phone;
}
