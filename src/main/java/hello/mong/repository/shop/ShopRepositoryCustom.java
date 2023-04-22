package hello.mong.repository.shop;

import hello.mong.domain.entity.Member;
import hello.mong.domain.response.shop.ShopListById;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepositoryCustom {
    List<ShopListById> shopListByMember(Member member);
}
