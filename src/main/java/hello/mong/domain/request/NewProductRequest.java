package hello.mong.domain.request;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class NewProductRequest {

    @NotEmpty
    private String productName;
    @NotEmpty
    private int productPrice;
    @NotEmpty
    private String shopName;
}
