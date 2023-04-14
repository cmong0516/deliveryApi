package hello.mong.domain.request;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class NewShopRequest {

    @NotEmpty
    private String shopName;
    @NotEmpty
    private String shopPhone;
    @NotEmpty
    private String city;
}
