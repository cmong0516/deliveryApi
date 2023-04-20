package hello.mong;

import hello.mong.controller.MemberController;
import hello.mong.domain.request.LoginMemberRequest;
import hello.mong.domain.request.LoginMemberRequest.LoginMemberRequestBuilder;
import hello.mong.domain.request.NewMemberRequest;
import hello.mong.domain.response.LoginMemberResponse;
import hello.mong.domain.response.SignUpMemberResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class MongApplicationTests {

	@Autowired
	MemberController memberController;

	@Test
	void contextLoads() {
	}

	@Test
	void signUp() {
		NewMemberRequest request = NewMemberRequest.builder()
				.email("test@test")
				.password("asdf1234")
				.name("tester")
				.phone("02-111-111").build();
		ResponseEntity<SignUpMemberResponse> member = memberController.createMember(request);

		SignUpMemberResponse body = member.getBody();

		Assertions.assertThat(body.getEmail()).isEqualTo(request.getEmail());
	}

	@Test
	void login() {
		NewMemberRequest request = NewMemberRequest.builder()
				.email("test@test")
				.password("asdf1234")
				.name("tester")
				.phone("02-111-111").build();
		memberController.createMember(request);

		LoginMemberRequest loginMemberRequest = LoginMemberRequest.builder().email("test@test")
				.password("asdf1234").build();

		ResponseEntity<LoginMemberResponse> loginMemberResponseEntity = memberController.loginMember(
				loginMemberRequest);

		LoginMemberResponse body = loginMemberResponseEntity.getBody();

		System.out.println("body = " + body);

	}

}
