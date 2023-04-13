package hello.mong.domain.request;

import lombok.Data;

@Data
public class NewShopRequest {
    private String name;
    private String phone;
    private String city;
}
