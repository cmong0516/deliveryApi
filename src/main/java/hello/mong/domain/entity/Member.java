package hello.mong.domain.entity;

import hello.mong.auditing.BaseTimeEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String name;
    private String password;
    private String phone;

    @OneToMany(mappedBy = "member",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Authority> roles = new ArrayList<>();

    @OneToMany
    private List<Shop> shops = new ArrayList<>();

    public void setRoles(List<Authority> roles) {
        this.roles = roles;
        roles.forEach(o -> o.setMember(this));
    }

    public void setShops(Shop shop) {
        this.shops.add(shop);
        shop.setMaster(this);
    }

    public Member(Long id, String email, String name, String password, String phone,
                  List<Shop> shops) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.shops = shops;
    }
}
