package hello.mong.domain.request.delivery;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class DeliveryPickUpRequest {
    @NotEmpty
    private Long orderId;
}
