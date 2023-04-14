package hello.mong.domain.request;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class NewMemberRequest {
    @NotEmpty
    private String email;
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;
    @NotEmpty
    private String phone;
}
