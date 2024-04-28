package thecommerceproject.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thecommerceproject.domain.Member;
import thecommerceproject.dto.request.MemberRequestDto;
import thecommerceproject.dto.request.UpdateMemberDto;
import thecommerceproject.service.MemberService;

import javax.validation.Valid;
import java.net.URI;

@Api(tags = "member API")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원가입
     * @param memberRequestDto 입력한 회원 정보
     * @return ResponseEntity
     */
    @ApiOperation(value = "회원 가입")
    @PostMapping("/member/create")
    public ResponseEntity createMember(@Valid @RequestBody MemberRequestDto memberRequestDto) throws Exception {
        memberService.createMember(memberRequestDto);
        return ResponseEntity.created(URI.create("/member/create")).build();
    }

    /**
     *
     * @param page 해당 페이지 0부터 시작
     * @param pageSize 내용 수 제한
     * @param sort 정렬 기준 0일때 가입일 순, 1일때 이름순
     * @return Page<Member> 페이지 설정에 해당 되는 회원 리스트
     */
    @ApiOperation(value = "회원 목록 조회")
    @GetMapping("/member/search")
    public Page<Member> searchMember(@RequestParam("page") int page,@RequestParam("pageSize") int pageSize,@RequestParam("sort") int sort) {
        return memberService.searchMember(page,pageSize, sort);
    }

    /**
     *
     * @param memberId 회원 아이디
     * @param updateMemberDto 수정 정보
     * @return Member 수정된 회원 정보
     */
    @ApiOperation(value = "회원 정보 수정")
    @PutMapping("/member/update/{memberId}")
    public Member updateMember(@PathVariable String memberId, @Valid @RequestBody UpdateMemberDto updateMemberDto) {
        return memberService.updateMember(memberId, updateMemberDto);
    }


}
