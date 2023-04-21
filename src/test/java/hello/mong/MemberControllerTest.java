package hello.mong;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.mong.controller.MemberController;
import hello.mong.domain.request.LoginMemberRequest;
import hello.mong.domain.request.NewMemberRequest;
import hello.mong.domain.response.LoginMemberResponse;
import hello.mong.domain.response.SignUpMemberResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MemberController memberController;

    @Test
    void signUp() throws Exception {
        NewMemberRequest request = NewMemberRequest.builder()
                .email("test@test")
                .password("asdf1234")
                .name("tester")
                .phone("02-111-111").build();

        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/member/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void login() throws Exception {
        NewMemberRequest request = NewMemberRequest.builder()
                .email("test@test")
                .password("asdf1234")
                .name("tester")
                .phone("02-111-111").build();
        memberController.createMember(request);

        LoginMemberRequest loginMemberRequest = LoginMemberRequest.builder().email("test@test")
                .password("asdf1234").build();

        String json = objectMapper.writeValueAsString(loginMemberRequest);

        mockMvc.perform(post("/member/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print());

    }
}
