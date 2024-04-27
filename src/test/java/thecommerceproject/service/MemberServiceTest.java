package thecommerceproject.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import thecommerceproject.domain.Member;
import thecommerceproject.dto.request.MemberRequestDto;
import thecommerceproject.repository.MemberRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    MemberRepository memberRepository;

    @InjectMocks
    MemberService memberService;

    @Test
    @DisplayName("회원 가입")
    public void createMemberSuccess() throws Exception {
        MemberRequestDto memberRequestDto = new MemberRequestDto(
                "testId",
                "testPwd",
                "testNick",
                "testName",
                "01011112222",
                "testEmail@test.com"
        );

        Member member = new Member(
                memberRequestDto.getMemberId(),
                memberRequestDto.getMemberPwd(),
                memberRequestDto.getNickname(),
                memberRequestDto.getName(),
                memberRequestDto.getPhoneNumber(),
                memberRequestDto.getEmail()
        );

        //given과 같은 동작으로 repository에 member를 넣고 결과값으로 member 객체 return
        when(memberRepository.save(any(Member.class))).thenReturn(member);
        Member returnMember = memberService.createMember(memberRequestDto);
        Assertions.assertEquals("testId", returnMember.getMemberId());
        Assertions.assertEquals("testPwd", returnMember.getMemberPwd());
        Assertions.assertEquals("testNick", returnMember.getNickname());
        Assertions.assertEquals("testName", returnMember.getName());
        Assertions.assertEquals("01011112222", returnMember.getPhoneNumber());
        Assertions.assertEquals("testEmail@test.com", returnMember.getEmail());
    }

}