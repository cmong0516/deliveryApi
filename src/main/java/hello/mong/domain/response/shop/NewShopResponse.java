package hello.mong.domain.response.shop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewShopResponse {

    private String shopName;
    private String shopPhone;
    private String city;
    private String masterName;
    private String masterPhone;
    private String masterEmail;
}
