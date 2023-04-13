package hello.mong.controller;

import hello.mong.domain.request.NewMemberRequest;
import hello.mong.domain.request.LoginMemberRequest;
import hello.mong.domain.response.LoginMemberResponse;
import hello.mong.domain.response.NewDeliveryResponse;
import hello.mong.domain.response.SignUpMemberResponse;
import hello.mong.service.MemberService;
import hello.mong.utils.JwtProvider;
import javax.servlet.http.HttpServletRequest;
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

    @PostMapping("/member/test")
    public String test(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        String substring = authorization.split(" ")[1].trim();
        String member = jwtProvider.getMember(substring);

        return member + " 님이 성공적으로 로그인 되었습니다.";
    }

}
