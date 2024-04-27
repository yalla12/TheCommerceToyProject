package thecommerceproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import thecommerceproject.dto.request.MemberRequestDto;
import thecommerceproject.service.MemberService;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원가입
     * @param memberRequestDto 입력한 회원 정보
     * @return ResponseEntity
     */
    @PostMapping("/member/create")
    public ResponseEntity createMember(@RequestBody MemberRequestDto memberRequestDto) throws Exception {
        memberService.createMember(memberRequestDto) ;
        return ResponseEntity.created(URI.create("/member/create")).build();
    }
}
