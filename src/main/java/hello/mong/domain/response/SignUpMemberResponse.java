package hello.mong.domain.response;

import hello.mong.domain.entity.Authority;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class SignUpMemberResponse {

    private String message;
    private String email;
    private String name;
    private LocalDateTime createDate;
    private List<Authority> roles = new ArrayList<>();

    public SignUpMemberResponse(String email, String name,List<Authority> role,LocalDateTime createDate) {
        this.message = "회원가입에 성공하였습니다.";
        this.email = email;
        this.name = name;
        this.createDate = createDate;
        this.roles = role;
    }
}
