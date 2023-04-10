package hello.mong.domain.response;

import hello.mong.domain.Role;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class SignUpMemberResponse {

    private String messge;
    private String email;
    private String name;
    private LocalDateTime createDate;
    private Role role;

    public SignUpMemberResponse(String email, String name,Role role,LocalDateTime createDate) {
        this.messge = "회원가입에 성공하였습니다.";
        this.email = email;
        this.name = name;
        this.createDate = createDate;
        this.role = role;
    }
}
