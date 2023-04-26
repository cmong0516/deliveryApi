package hello.mong.domain.response.member;

import hello.mong.domain.entity.Authority;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Data;

@Data
public class LoginMemberResponse {

    private String message;
    private String email;
    private Set<Authority> roles = new HashSet<>();
    private String token;

    public LoginMemberResponse(String message, String email,Set<Authority> roles ,String token) {
        this.message = message;
        this.email = email;
        this.roles = roles;
        this.token = token;
    }
}
