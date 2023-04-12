package hello.mong.domain.request;

import lombok.Data;

@Data
public class NewMemberRequest {

    private String email;
    private String name;
    private String password;
    private String phone;
}
