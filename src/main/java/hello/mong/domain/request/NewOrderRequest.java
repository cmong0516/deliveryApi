package hello.mong.domain.request;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class NewOrderRequest {
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String productName;
    @NotEmpty
    private int productQuantity;
}
