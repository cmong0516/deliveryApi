package hello.mong.domain.request.shop;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewShopRequest {

    @NotEmpty
    private String shopName;
    @NotEmpty
    private String shopPhone;
    @NotEmpty
    private String city;
}
