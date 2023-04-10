package hello.mong.domain.request;

import lombok.Data;

@Data
public class CreateMemberForm {

    private String email;
    private String name;
    private String password;
}
