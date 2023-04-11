package hello.mong.domain.request;

import lombok.Data;

@Data
public class CreateMemberRequest {

    private String email;
    private String name;
    private String password;
}
