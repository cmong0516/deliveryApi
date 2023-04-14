package hello.mong.domain.request;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class DeliveryPickUpRequest {
    @NotEmpty
    private Long orderId;
}
