package hello.mong.domain.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewOrderRequest {
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String productName;
    @NotNull
    private int productQuantity;
}
