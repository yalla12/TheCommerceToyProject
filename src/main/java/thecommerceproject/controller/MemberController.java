package thecommerceproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import thecommerceproject.dto.request.MemberRequestDto;
import thecommerceproject.service.MemberService;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원가입
     * @param memberRequestDto 입력한 회원 정보
     * @return String
     */
    @PostMapping("/member/create")
    public String createMember(@RequestBody MemberRequestDto memberRequestDto) {

        return memberService.createMember(memberRequestDto) ;
    }
}
