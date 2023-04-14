package hello.mong.domain.request;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginMemberRequest {
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}
