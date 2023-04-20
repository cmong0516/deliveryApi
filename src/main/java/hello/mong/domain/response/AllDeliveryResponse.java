package hello.mong.domain.response;

import com.querydsl.core.annotations.QueryProjection;
import java.util.List;
import lombok.Data;

@Data
public class AllDeliveryResponse {

    private Long id;
    private String name;
    private String phone;
    private List<Long> orderIds;

    @QueryProjection
    public AllDeliveryResponse(Long id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public void setOrderIds(List<Long> orderIds) {
        this.orderIds = orderIds;
    }
}
