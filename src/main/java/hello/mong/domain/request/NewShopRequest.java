package hello.mong.domain.request;

import lombok.Data;

@Data
public class NewShopRequest {
    private String shopName;
    private String shopPhone;
    private String city;
}
