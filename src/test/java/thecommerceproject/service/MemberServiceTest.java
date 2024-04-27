package thecommerceproject.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import thecommerceproject.domain.Member;
import thecommerceproject.dto.request.MemberRequestDto;
import thecommerceproject.repository.MemberRepository;

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

    @Test
    @DisplayName("회원 목록 조회")
    void searchMember() {
        // 임시 데이터 삽입
        for(int i = 0; i < 10; i++) {
            memberRepository.save( new Member(
                    "testId"+i,
                    "testPwd"+i,
                    "testNick"+i,
                    "testName"+i,
                    "01011112222+i",
                    "testEmail@test.com+i"
            ));
        }
        // 0번 페이지 2개씩 가입일 기준 정렬
        Pageable pageable = PageRequest.of(0, 2, Sort.Direction.ASC, "createAt");

        //1번 페이지 3개씩 이름 기준 정렬
//        Pageable pageable = PageRequest.of(1, 3, Sort.Direction.ASC, "name");

        Page<Member> result = memberRepository.findAll(pageable);

        System.out.println("@@@@@@@@@@@@@@@@ 결과 확인 @@@@@@@@@@@@@@@");
        for(int i = 0; i < result.getSize(); i++) {
            System.out.print("memberId: " + result.getContent().get(i).getMemberId() + ", ");
            System.out.print("name: " + result.getContent().get(i).getName() + ", ");
            System.out.print("createAt: " + result.getContent().get(i).getCreateAt() + ", ");
            System.out.println();

        }

    }

}