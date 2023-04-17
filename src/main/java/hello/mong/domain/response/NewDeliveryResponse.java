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
    private String phone;
    private String token;
}
