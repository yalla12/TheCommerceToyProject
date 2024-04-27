package thecommerceproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thecommerceproject.domain.Member;
import thecommerceproject.dto.request.MemberRequestDto;
import thecommerceproject.repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     *
     * @param memberRequestDto 입력한 회원 정보
     * @Return Member
     */
    @Transactional
    public Member createMember(MemberRequestDto memberRequestDto) throws Exception {

        //아이디 중복체크
        boolean dupMemberId = memberRepository.existsByMemberId(memberRequestDto.getMemberId());
        if(dupMemberId) throw new RuntimeException("아이디 중복");

        //전화번호 중복체크
        boolean dupphoneNumber =  memberRepository.existsByPhoneNumber(memberRequestDto.getPhoneNumber());
        if(dupphoneNumber) throw new RuntimeException("전화번호 중복");

        Member member = new Member(
                memberRequestDto.getMemberId(),
                memberRequestDto.getMemberPwd(),
                memberRequestDto.getNickname(),
                memberRequestDto.getName(),
                memberRequestDto.getPhoneNumber(),
                memberRequestDto.getEmail()
        );

        return memberRepository.save(member);
    }

    /**
     *
     * @param offset 해당 페이지 0부터 시작
     * @param limit 내용 수 제한
     * @param sort 정렬 기준 0일때 가입일 순, 1일때 이름순
     * @return Page<Member> 페이지 설정에 해당 되는 회원 리스트
     */
    @Transactional(readOnly = true)
    public Page<Member> SearchMember(int offset, int limit, int sort) {
        Sort sortby;
        if(sort == 0) {
            sortby = Sort.by("createAt").ascending();
        } else {
           sortby = Sort.by("name").ascending();
        }
        Pageable pageable = PageRequest.of(offset, limit, sortby);

        return memberRepository.findAll(pageable);
    }


}
