package hello.mong.domain.request;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewProductRequest {

    @NotEmpty
    private String productName;
    @NotEmpty
    private int productPrice;
    @NotEmpty
    private String shopName;
}
