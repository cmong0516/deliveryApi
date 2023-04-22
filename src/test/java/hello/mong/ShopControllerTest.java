package hello.mong;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.mong.controller.MemberController;
import hello.mong.controller.ShopController;
import hello.mong.domain.request.member.LoginMemberRequest;
import hello.mong.domain.request.member.NewMemberRequest;
import hello.mong.domain.request.shop.NewShopRequest;
import hello.mong.domain.response.member.LoginMemberResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ShopControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ShopController shopController;

    @Autowired
    private MemberController memberController;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void newShop() throws Exception {

        NewMemberRequest newMemberRequest = NewMemberRequest.builder()
                .phone("010-9999-9999")
                .name("tester")
                .password("asdf1234")
                .email("test@test")
                .build();

        memberController.createMember(newMemberRequest);

        LoginMemberRequest loginMemberRequest = LoginMemberRequest.builder()
                .email("test@test")
                .password("asdf1234")
                .build();

        ResponseEntity<LoginMemberResponse> loginMemberResponse = memberController.loginMember(
                loginMemberRequest);

        String loginToken = loginMemberResponse.getBody().getToken();

        System.out.println("loginToken = " + loginToken);

        NewShopRequest shopRequest = NewShopRequest.builder()
                .shopName("testShop")
                .city("testCity")
                .shopPhone("011-1111-1111")
                .build();

        String json = objectMapper.writeValueAsString(shopRequest);

        mockMvc.perform(post("/shop/new")
                        .header("Authorization","Bearer " + loginToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.shopName").value("testShop"))
                .andExpect(jsonPath("$.shopPhone").value("011-1111-1111"))
                .andExpect(jsonPath("$.city").value("TESTCITY"))
                .andDo(print());
    }
}
