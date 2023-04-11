package hello.mong.domain.request;

import lombok.Data;

@Data
public class LoginMemberRequest {

    private String email;
    private String password;
}
