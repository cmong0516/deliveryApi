package hello.mong.domain.response.shop;

import hello.mong.domain.response.member.AllMemberResponse;
import hello.mong.domain.response.product.ProductResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AllShopResponse {

    private Long shopId;
    private String shopName;
    private String shopPhone;
    private String city;
    private AllMemberResponse master;
    private List<ProductResponse> product;

}
