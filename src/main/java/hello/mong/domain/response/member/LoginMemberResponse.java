package hello.mong.domain.response.member;

import hello.mong.domain.entity.Authority;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class LoginMemberResponse {

    private String message;
    private String email;
    private List<Authority> roles = new ArrayList<>();
    private String token;

    public LoginMemberResponse(String message, String email,List<Authority> roles ,String token) {
        this.message = message;
        this.email = email;
        this.roles = roles;
        this.token = token;
    }
}
