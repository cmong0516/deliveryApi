package hello.mong.domain.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class AllMemberResponse {

    private Long id;
    private String email;
    private String username;
    private String phone;

    @QueryProjection
    public AllMemberResponse(Long id, String email, String username, String phone) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.phone = phone;
    }
}
