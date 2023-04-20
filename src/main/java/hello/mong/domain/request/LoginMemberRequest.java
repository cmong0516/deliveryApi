package hello.mong.domain.request;

import javax.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginMemberRequest {
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}
