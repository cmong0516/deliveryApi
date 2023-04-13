package hello.mong.domain.response;

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
}
