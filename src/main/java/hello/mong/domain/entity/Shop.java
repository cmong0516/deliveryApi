package hello.mong.domain.entity;

import hello.mong.auditing.BaseTimeEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Shop extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;
    private String city;
    @ManyToOne
    private Member master;

    @OneToMany(mappedBy = "shop")
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        this.products.add(product);
        product.setShop(this);
    }
}

// Jwt 토큰의 member 를 조회하여 Shop 을 등록할때 Member Type 의 master 를 설정해야함 .
