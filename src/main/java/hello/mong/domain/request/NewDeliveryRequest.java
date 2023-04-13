package hello.mong.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewDeliveryRequest {
    private String name;
    private int age;
    private String city;
    private String phone;
}
