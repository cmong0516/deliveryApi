package hello.mong.controller;

import hello.mong.domain.request.CreateMemberForm;
import hello.mong.domain.response.LoginMemberResponse;
import hello.mong.domain.response.SignUpMemberResponse;
import hello.mong.service.MemberService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final MemberService memberService;

    @PostMapping("/member/new")
    public ResponseEntity<SignUpMemberResponse> createMember(@Valid @RequestBody CreateMemberForm createMemberForm) {
        SignUpMemberResponse signUpMemberResponse = memberService.signUp(createMemberForm);

        return new ResponseEntity<>(signUpMemberResponse, HttpStatus.OK);
    }

    @PostMapping("/member/login")
    public ResponseEntity<LoginMemberResponse> loginMember() {
        return new ResponseEntity<>(new LoginMemberResponse(), HttpStatus.OK);
    }
}
