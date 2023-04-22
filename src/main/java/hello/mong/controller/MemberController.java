package hello.mong.controller;

import hello.mong.domain.request.member.NewMemberRequest;
import hello.mong.domain.request.member.LoginMemberRequest;
import hello.mong.domain.response.member.LoginMemberResponse;
import hello.mong.domain.response.member.SignUpMemberResponse;
import hello.mong.service.MemberService;
import hello.mong.utils.JwtProvider;
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
public class MemberController {

    private final MemberService memberService;
    private final JwtProvider jwtProvider;

    @PostMapping("/member/new")
    public ResponseEntity<SignUpMemberResponse> createMember(@Valid @RequestBody NewMemberRequest newMemberRequest) {
        SignUpMemberResponse signUpMemberResponse = memberService.signUp(newMemberRequest);

        return new ResponseEntity<>(signUpMemberResponse, HttpStatus.OK);
    }

    @PostMapping("/member/login")
    public ResponseEntity<LoginMemberResponse> loginMember(@Valid @RequestBody LoginMemberRequest request) {

        String token = memberService.login(request);

        return new ResponseEntity<>(new LoginMemberResponse("로그인에 성공하였습니다.", request.getEmail(), token), HttpStatus.OK);
    }


}
