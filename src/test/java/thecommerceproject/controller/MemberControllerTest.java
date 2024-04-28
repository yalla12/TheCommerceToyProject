package thecommerceproject.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import thecommerceproject.domain.Member;
import thecommerceproject.dto.request.MemberRequestDto;
import thecommerceproject.dto.request.UpdateMemberDto;
import thecommerceproject.service.MemberService;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
@MockBean(JpaMetamodelMappingContext.class)
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // MemberController에서 잡고 있는 Bean 객체에 Mock 형태의 객체를 생성
    @MockBean
    MemberService memberService;

    @Test
    @DisplayName("회원 가입 테스트")
    void createMemberTest() throws Exception {

        // body값 설정
        MemberRequestDto memberRequestDto = new MemberRequestDto(
                "testId",
                "testPwd",
                "testNick",
                "testName",
                "01011112222",
                "testEmail@test.com"
        );

        /*
        mock 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
        memeberService클래스에서 createMember메소드에 memberRequestDto파라미터를 넣고 호출했을때
        meberRequestDto객체를 리턴한다.
        */
        given(memberService.createMember(memberRequestDto)).willReturn(
                new Member(
                        "testId",
                        "testPwd",
                        "testNick",
                        "testName",
                        "01011112222",
                        "testEmail@test.com"
                )
        );

        // 객체를 json 형태로 변환
        Gson gson = new Gson();
        String content = gson.toJson(memberRequestDto);

        // api 호출 테스트
        mockMvc.perform(
                post("/member/create")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());

        // 해당 객체의 메소드가 실행되었는지 체크
        verify(memberService).createMember(memberRequestDto);
    }


    @Test
    @DisplayName("회원 목록 조회")
    void searchMember() throws Exception {

        given(memberService.searchMember(0,2, 0)).willReturn(Page.empty());

        mockMvc.perform(
                        get("/member/search")
                                .param("page","0")
                                .param("pageSize","2")
                                .param("sort","0"))
                .andExpect(status().isOk())
                .andDo(print());

        verify(memberService).searchMember(0,2,0);

    }


    @Test
    @DisplayName("회원 정보 수정")
    void updateMember() throws Exception {

        UpdateMemberDto updateMemberDto = new UpdateMemberDto(
                "UpdatePwd",
                "UpdateNick",
                "UpdateName",
                "01011112222",
                "UpdateEmail@test.com"
        );


        given(memberService.updateMember("testId", updateMemberDto)).willReturn(
                new Member(
                        "testId",
                        "UpdatePwd",
                        "UpdateNick",
                        "UpdateName",
                        "01011112222",
                        "UpdateEmail@test.com"
                )
        );

        Gson gson = new Gson();
        String content = gson.toJson(updateMemberDto);

        // api 호출 테스트
        mockMvc.perform(
                        put("/member/update/testId")
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

    }


}