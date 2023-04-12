package hello.mong.domain.response;

import hello.mong.domain.entity.Authority;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class LoginMemberResponse {

    private String message;
    private String email;
    private String token;

    public LoginMemberResponse(String message, String email,String token) {
        this.message = message;
        this.email = email;
        this.token = token;
    }
}
