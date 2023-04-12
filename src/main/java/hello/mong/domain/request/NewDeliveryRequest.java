package hello.mong.domain.request;

import lombok.Data;

@Data
public class NewDeliveryRequest {
    private String name;
    private int age;
    private String city;
    private String phone;
}
